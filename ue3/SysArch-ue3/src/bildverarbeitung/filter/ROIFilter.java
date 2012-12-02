/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.RawPackage;
import framework.filter.Filter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIFilter<in, out> extends Filter<in, out>  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    
    private Rectangle roi;

    public Rectangle getRoi() {
        return roi;
    }

    public void setRoi(Rectangle roi) {
        this.roi = roi;
    }

    public ROIFilter() {
        super();
    }

    public ROIFilter(Rectangle roi) {
        super();
        this.roi = roi;
    }

    @Override
    public boolean filter(in data) {
        RawPackage input = (RawPackage) data;
        BufferedImage img = (BufferedImage) input.getImg();
        PlanarImage image = PlanarImage.wrapRenderedImage(img);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(roi, image.getColorModel()));
        ROIPackage roiImage = new ROIPackage(image, input.getImg(), roi, true);
        result = (out) roiImage;

        return true;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        change.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        change.removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
