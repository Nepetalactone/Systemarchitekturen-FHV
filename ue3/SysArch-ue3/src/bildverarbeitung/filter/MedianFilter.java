/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.MedianPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import framework.filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.media.jai.JAI;
import javax.media.jai.operator.MedianFilterDescriptor;

/**
 *
 * @author Tobias
 */
public class MedianFilter<in, out> extends Filter<in, out>  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int maskSize;

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
    }
    
    public MedianFilter() {
        super();
        maskSize = 3; //default size
    }

    @Override
    public boolean filter(in data) {


        ThreshPackage ti = (ThreshPackage) data;
        ParameterBlock pb = new ParameterBlock();

        pb.addSource(ti.getImage());
        pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        pb.add(maskSize);

        RenderedImage input = JAI.create("medianfilter", pb, null);
        MedianPackage medianPack = new MedianPackage(ti.getOriginal(), input);

        result = (out) medianPack;
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
