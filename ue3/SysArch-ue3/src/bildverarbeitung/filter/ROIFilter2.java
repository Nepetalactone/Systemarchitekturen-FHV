/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.DilatePackage;
import bildverarbeitung.filterObjects.ROIPackage;
import framework.filter.Filter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.PlanarImage;
import framework.pipe.IPipe;

/**
 *
 * @author Tobias
 */
public class ROIFilter2<in, out> extends Filter<in, out> {
	
    @Override
    public boolean filter(in data) {
        DilatePackage rp = (DilatePackage) data;
        Rectangle[] rects = new Rectangle[]{
            new Rectangle(0, 10, 20, 57),
            new Rectangle(40, 10, 60, 57),
            new Rectangle(100, 10, 60, 57),
            new Rectangle(160, 10, 80, 57),
            new Rectangle(240, 10, 60, 57),
            new Rectangle(300, 10, 60, 57),
            new Rectangle(360, 10, 60, 57)
        };

        ROIFilter filter;
        for (Rectangle rect : rects) {
            RenderedImage img = (RenderedImage) rp.getImage();
            PlanarImage image = PlanarImage.wrapRenderedImage(img);

            image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(rect, image.getColorModel()));
            ROIPackage roiImage = new ROIPackage(image, convertRenderedImage(rp.getImage()), rect, false);

            result = (out) roiImage;

            for (IPipe pipe : outputPipes) {
                try {
                    pipe.push(result);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ROIFilter2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

    public BufferedImage convertRenderedImage(RenderedImage img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }
    
	public ROIFilter2(){
		super();
	}
	
	
}
