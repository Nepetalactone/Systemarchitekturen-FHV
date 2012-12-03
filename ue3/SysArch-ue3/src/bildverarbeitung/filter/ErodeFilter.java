/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.ErodePackage;
import bildverarbeitung.filterObjects.IImagePackage;
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
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ErodeFilter extends Filter  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    IImagePackage workingCopy = null;
    
    public ErodeFilter() {
        super();
    }

    @Override
    public boolean filter(Object data) {
        KernelJAI k = new KernelJAI(11, 11, new float[]{
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,});

        ParameterBlock pb = new ParameterBlock();
        this.workingCopy = (IImagePackage) data;
        RenderedImage a = workingCopy.getImage();
        BufferedImage b = ImageFileHelper.getDeepCopy(ImageFileHelper.convertRenderedImageToBufferedImage(a));
        

        pb.addSource(b);
        pb.add(k);

        PlanarImage image = JAI.create("Erode", pb);
        BufferedImage asd = image.getAsBufferedImage();
        ErodePackage eroPack = new ErodePackage(workingCopy.getOriginal(), asd);

        result = eroPack;
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
            Logger.getLogger(ErodeFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
