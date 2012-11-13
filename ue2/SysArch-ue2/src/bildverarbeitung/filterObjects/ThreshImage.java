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
public class ThreshImage  implements IImagePackage {

    
    private RenderedImage image;
    private BufferedImage original;

    public ThreshImage(RenderedImage image,BufferedImage original) {
        this.image = image;
        this.original = original;
    }
    
    @Override
    public RenderedImage getImage() {
        return image;
    }

    @Override
    public BufferedImage getOriginal() {
        return original;
    }
    
}
