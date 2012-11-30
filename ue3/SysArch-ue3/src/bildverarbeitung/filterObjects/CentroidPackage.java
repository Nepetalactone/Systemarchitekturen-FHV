/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 *
 * @author Tobias
 */
public class CentroidPackage implements IImagePackage{
    
    private BufferedImage original;
    private RenderedImage image;
    private Point center;

    
    public CentroidPackage(BufferedImage original, RenderedImage image, Point center) {
        this.original = original;
        this.image = image;
        this.center = center;
    }
    
    @Override
    public RenderedImage getImage() {
        return image;
    }

    @Override
    public BufferedImage getOriginal() {
        return original;
    }
    
    public Point getCenter(){
        return center;
    }

    @Override
    public void setImage(RenderedImage img) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOriginal(BufferedImage img) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
