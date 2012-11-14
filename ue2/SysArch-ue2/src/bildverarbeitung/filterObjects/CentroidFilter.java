/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;

/**
 *
 * @author Tobias
 */
public class CentroidFilter extends Filter{

    @Override
    public boolean filter(Object data) {
        DilatePackage dilPack = (DilatePackage) data;
        ParameterBlock pb = new ParameterBlock();
        
        pb.addSource(dilPack);
        RenderedImage img = JAI.create("centroid", pb);
        
        return true;
    }
    
}
