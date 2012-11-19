/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.CentroidPackage;
import bildverarbeitung.filterObjects.ROIPackage;
import filter.Filter;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import test.Journal;

/**
 *
 * @author Tobias
 */
public class CentroidFilter extends Filter{

    @Override
    public boolean filter(Object data) {
        ROIPackage dilPack = (ROIPackage) data;
        
        BufferedImage b = convertRenderedImage(dilPack.getImage());
        Point center = getCenter(b);
        
        CentroidPackage centPack = new CentroidPackage(dilPack.getOriginal(),dilPack.getImage(),center);
        result = centPack;
        Journal.getInstance().addCenterPoint(center.x, center.y);
        return true;
        
    }
    
    
    private Point getCenter(BufferedImage b){
        int sumX = 0;
        int sumY = 0;
        int counter = 0;
        
        for(int y = 0; y < b.getHeight(); y++){
            for(int x = 0; x < b.getWidth(); x++){
                int clr = b.getRGB(x, y);
                int white = Color.white.getRGB();
                if(clr == white){
                    sumX += x;
                    sumY += y;
                    counter++;
                }
            }
        }
        
        int xx = Math.round(sumX / counter);
        int yy = Math.round(sumY / counter);
        return new Point(xx,yy);
    }
    
    private BufferedImage convertRenderedImage(RenderedImage img){
        if(img instanceof BufferedImage){
            return (BufferedImage) img;
        }
        
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        
        if(keys != null){
            for(int i = 0; i < keys.length; i++){
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster , isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }
    
    
}
