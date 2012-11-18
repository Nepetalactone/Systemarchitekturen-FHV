/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.RawPackage;
import darstellung.Painter;
import filter.Filter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIFilter<in,out> extends Filter<in,out>{

    private Rectangle roi;
    
    public ROIFilter(Rectangle roi){
        super();
        this.roi = roi;
    }
    
    
    @Override
    public boolean filter(in data) {
        RawPackage input = (RawPackage) data;
        BufferedImage img = (BufferedImage) input.getImg();
        PlanarImage image = PlanarImage.wrapRenderedImage(img);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(roi, image.getColorModel()));
        ROIPackage roiImage = new ROIPackage(image,input.getImg(),roi ,true);
        result = (out) roiImage;
        
        Painter p = new Painter("ROIFilter",roiImage);
        
        return true;
    }
    
    
    
}
