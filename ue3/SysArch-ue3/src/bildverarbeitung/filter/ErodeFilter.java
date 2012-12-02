/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.ErodePackage;
import bildverarbeitung.filterObjects.MedianPackage;
import framework.filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;

/**
 *
 * @author Tobias
 */
public class ErodeFilter extends Filter  implements PropertyChangeListener {

    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    
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
        MedianPackage medPack = (MedianPackage) data;

        pb.addSource(medPack.getImage());
        pb.add(k);

        RenderedImage img = JAI.create("Erode", pb);
        ErodePackage eroPack = new ErodePackage(medPack.getOriginal(), img);

        result = eroPack;

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
