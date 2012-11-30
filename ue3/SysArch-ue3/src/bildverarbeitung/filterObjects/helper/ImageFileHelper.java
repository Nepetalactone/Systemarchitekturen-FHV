/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects.helper;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author green
 */
public class ImageFileHelper {
    
    
    
    public static void saveImageToFile(String path, BufferedImage image) throws IOException{
        File f = new File(path);
        ImageIO.write(image, getExtension(f), f);
    }
    public static void saveImageToFile(String path, String extension, BufferedImage image) throws IOException{
        File f = new File(path+"."+extension);
        saveImageToFile(path,image);
    }
    
    public static BufferedImage loadImageFromFile(File f) throws IOException{
        return ImageIO.read(f);
    }
    public static BufferedImage loadImageFromFile(String path) throws IOException{
        return loadImageFromFile(new File(path));
    }
    public static BufferedImage loadImageFromFile(String path ,String extension) throws IOException{
        return loadImageFromFile(new File(path+"."+extension));
    }
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    public static BufferedImage getDeepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
}

