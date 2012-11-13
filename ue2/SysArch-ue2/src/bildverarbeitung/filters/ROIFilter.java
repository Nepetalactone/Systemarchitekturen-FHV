/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.ROIImage;
import bildverarbeitung.filterObjects.RawImage;
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

    @Override
    public boolean filter(in data) {
        RawImage input = (RawImage) data;
        Rectangle rectangle = new Rectangle((int)input.getPointA().getX(),(int)input.getPointA().getY(),(int)input.getPointB().getX(),(int)input.getPointB().getY());
        BufferedImage img = (BufferedImage) input.getImg();
        PlanarImage image = PlanarImage.wrapRenderedImage(img);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(rectangle, image.getColorModel()));
        result = (out) new ROIImage(image);
        return true;
    }
    
    
    
}
