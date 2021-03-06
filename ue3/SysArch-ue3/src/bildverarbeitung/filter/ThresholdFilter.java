/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.ROIPackage;
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
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ThresholdFilter extends Filter implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int lowValue;
    private int highValue;
    private int constantValue;
    private IImagePackage workingCopy;
    
    public int getLowValue() {
        return lowValue;
    }

    public void setLowValue(int lowValue) {
        int old = this.lowValue;
        this.lowValue = lowValue;
        change.firePropertyChange("width",old,lowValue);
    }

    public int getHighValue() {
        return highValue;
    }

    public void setHighValue(int highValue) {
        int old = this.highValue;
        this.highValue = highValue;
        change.firePropertyChange("width",old,highValue);
    }

    public int getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(int constantValue) {
        int old = this.constantValue;
        this.constantValue = constantValue;
        change.firePropertyChange("width",old,constantValue);
    }
    
    public ThresholdFilter() {
        super();
        //default Values
        lowValue = 0;
        highValue = 33;
        constantValue = 255;
    }

    @Override
    public boolean filter(Object data) {
        //0,30,255
        //0,254,0
        //
        workingCopy = (IImagePackage) data;
        double[] low = new double[]{lowValue, lowValue, lowValue};
        double[] high = new double[]{highValue, highValue, highValue};
        double[] constants = new double[]{constantValue, constantValue, constantValue};

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(ImageFileHelper.getDeepCopy(ImageFileHelper.convertRenderedImageToBufferedImage(workingCopy.getImage())));
        pb.add(low);
        pb.add(high);
        pb.add(constants);
        PlanarImage image = JAI.create("threshold", pb);
        BufferedImage asd = image.getAsBufferedImage();
        ThreshPackage threshPack = new ThreshPackage(workingCopy.getOriginal(), asd, false);
        result =  threshPack;
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
            Logger.getLogger(ThresholdFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
