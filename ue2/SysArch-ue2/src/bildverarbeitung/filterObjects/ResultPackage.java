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
public class ResultPackage implements IImagePackage {

    private BufferedImage original;
    private RenderedImage image;

    public ResultPackage(BufferedImage original, RenderedImage image) {
        this.original = original;
        this.image = image;
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
