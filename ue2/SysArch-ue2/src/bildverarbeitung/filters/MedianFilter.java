/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.MedianPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import darstellung.Painter;
import filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.operator.MedianFilterDescriptor;

/**
 *
 * @author Tobias
 */
public class MedianFilter<in,out> extends Filter<in,out>{

    @Override
    public boolean filter(in data) {
        
        
        ThreshPackage ti = (ThreshPackage) data;
        ParameterBlock pb = new ParameterBlock();

        pb.addSource(ti.getImage());
        pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        pb.add(3);
        
        RenderedImage input = JAI.create("medianfilter", pb, null);
        MedianPackage medianPack = new MedianPackage(ti.getOriginal(), input);
        Painter p = new Painter("Median", medianPack);
        
        result = (out)medianPack;
        return true;
    }
    
}
