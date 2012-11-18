/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 *
 * @author Tobias
 */
public class ROI2Package implements IImagePackage{
    
    private RenderedImage image;
    private BufferedImage original;
    
    @Override
    public RenderedImage getImage() {
        return image;
    }

    @Override
    public BufferedImage getOriginal() {
        return original;
    }

    public ROI2Package(RenderedImage image, BufferedImage original) {
        this.image = image;
        this.original = original;
    }
    
}
