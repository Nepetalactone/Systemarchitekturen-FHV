/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.endpoint.DataSink;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class Sink extends DataSink implements PropertyChangeListener {
    
    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private int i;
    
    public Sink(){
    	super();
        i = 0;
    }
    public Sink(boolean isActive){
        super(isActive);
        i = 0;
    }

    @Override
    public void saveData(Object data) {
        if(data instanceof IImagePackage){
            try {
                IImagePackage p = (IImagePackage) data;
                ImageFileHelper.saveImageToFile("FilteredImage_"+i+".png", ImageFileHelper.convertRenderedImageToBufferedImage(p.getImage()));
                i++;
            } catch (IOException ex) {
                Logger.getLogger(Sink.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                push((IImagePackage) evt.getNewValue());
            }
        } catch (Exception ex) {
            Logger.getLogger(ROIFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
