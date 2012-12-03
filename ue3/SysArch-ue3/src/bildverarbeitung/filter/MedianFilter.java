/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.MedianPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.filter.Filter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.JAI;
import javax.media.jai.operator.MedianFilterDescriptor;

/**
 *
 * @author Tobias
 */
public class MedianFilter extends Filter  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int maskSize;
    private IImagePackage workingCopy = null;
    
    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        int oldMaskSize = this.maskSize;
        this.maskSize = maskSize;
        change.firePropertyChange("maskSize", oldMaskSize, maskSize);
    }
    
    public MedianFilter() {
        super();
        maskSize = 3; //default size
        change.addPropertyChangeListener(this);
    }

    @Override
    public boolean filter(Object data) {


        workingCopy = (IImagePackage) data;
        BufferedImage b = ImageFileHelper.getDeepCopy(ImageFileHelper.convertRenderedImageToBufferedImage(workingCopy.getImage()));

        ParameterBlock pb = new ParameterBlock();

        pb.addSource(b);
        pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        pb.add(maskSize);

        RenderedImage input = JAI.create("medianfilter", pb, null);
        MedianPackage medianPack = new MedianPackage(workingCopy.getOriginal(), input);

        result = medianPack;
        change.firePropertyChange("result",this,result);
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
            if(evt.getPropertyName().equals("result")){
                if(!(evt.getOldValue() == this)){
                    push((IImagePackage) evt.getNewValue());
                }
            }else{
                push(workingCopy);
            }
        } catch (Exception ex) {
            Logger.getLogger(MedianFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
