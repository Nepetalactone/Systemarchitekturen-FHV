/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.DilatePackage;
import bildverarbeitung.filterObjects.ROI2Package;
import bildverarbeitung.filterObjects.ROIPackage;
import filter.Filter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIFilter2<in, out> extends Filter<in, out> {

    @Override
    public boolean filter(in data) {
        DilatePackage rp = (DilatePackage) data;
        Rectangle[] rects = new Rectangle[]{new Rectangle(0, 0, 40, 57),
            new Rectangle(40, 0, 60, 57),
            new Rectangle(100, 0, 160, 57),
            new Rectangle(160, 0, 240, 57),
            new Rectangle(240, 0, 300, 57),
            new Rectangle(300, 0, 360, 57),
            new Rectangle(360, 0, 420, 57)};

        ROIFilter filter;
        for (Rectangle rect : rects) {
            BufferedImage img = (BufferedImage) rp.getOriginal();
            PlanarImage image = PlanarImage.wrapRenderedImage(img);
            image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(rect, image.getColorModel()));
            ROIPackage roiImage = new ROIPackage(image, rp.getOriginal(), rect, true);
            result = (out) roiImage;
        }

        return true;
    }
}
