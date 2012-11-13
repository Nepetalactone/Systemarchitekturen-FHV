/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.ThreshImage;
import filter.Filter;
import java.awt.image.renderable.ParameterBlock;

/**
 *
 * @author Tobias
 */
public class MedianFilter<in,out> extends Filter<in,out>{

    @Override
    public boolean filter(in data) {
        
        
        ThreshImage ti = (ThreshImage) data;
        
        
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(ti.getImage());
        
        
        return false;
    }
    
}
