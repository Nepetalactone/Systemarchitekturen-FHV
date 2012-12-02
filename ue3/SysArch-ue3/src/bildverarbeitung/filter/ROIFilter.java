/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.RawPackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.filter.Filter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIFilter<in, out> extends Filter<in, out>  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int x;
    private int y;
    private int height;
    private int width;
    private IImagePackage workingCopy;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        int old = this.x;
        this.x = x;
        change.firePropertyChange("X", old, x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        int old = this.y;
        this.y = y;
        change.firePropertyChange("Y", old, y);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        int old = this.height;
        this.height = height;
        change.firePropertyChange("Height",old,height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        int old = this.width;
        this.width = width;
        change.firePropertyChange("Width",old,width);
    }
    
    public ROIFilter() {
        super();
        change.addPropertyChangeListener(this);
        //default values
        x = 0;
        y = 50;
        width = 447;
        height = 55;
    }

    public ROIFilter(int x, int y, int height, int width) {
        super();
        change.addPropertyChangeListener(this);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean filter(in data) {
        Rectangle roi = new Rectangle(x, y, width, height);
        workingCopy = (IImagePackage) data;
        BufferedImage img = (BufferedImage) ImageFileHelper.getDeepCopy(ImageFileHelper.convertRenderedImageToBufferedImage(workingCopy.getImage()));
        PlanarImage image = PlanarImage.wrapRenderedImage(img);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(roi, image.getColorModel()));
        ROIPackage roiImage = new ROIPackage(image, ImageFileHelper.convertRenderedImageToBufferedImage(workingCopy.getImage()), roi, true);
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
        try {
            if (workingCopy != null) {
                push((in) workingCopy);
            }
        } catch (Exception ex) {
            Logger.getLogger(ROIFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
