/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import framework.filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.media.jai.JAI;

/**
 *
 * @author Tobias
 */
public class ThresholdFilter<in, out> extends Filter<in, out> implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int lowValue;
    private int highValue;
    private int constantValue;

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
    public boolean filter(in data) {
        //0,30,255
        //0,254,0
        //
        ROIPackage roiImage = (ROIPackage) data;
        double[] low = new double[]{lowValue, lowValue, lowValue};
        double[] high = new double[]{highValue, highValue, highValue};
        double[] constants = new double[]{constantValue, constantValue, constantValue};

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(roiImage.getImage());
        pb.add(low);
        pb.add(high);
        pb.add(constants);
        RenderedImage thresh = JAI.create("threshold", pb);
        ThreshPackage threshPack = new ThreshPackage(thresh, roiImage.getOriginal(), false);
        result = (out) threshPack;
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
