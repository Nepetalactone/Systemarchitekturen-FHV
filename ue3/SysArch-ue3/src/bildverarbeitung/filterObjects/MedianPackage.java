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
public class MedianPackage implements IImagePackage {

    private BufferedImage original;
    private RenderedImage renderedImage;

    public RenderedImage getRenderedImage() {
        return renderedImage;
    }

    public void setRenderedImage(RenderedImage renderedImage) {
        this.renderedImage = renderedImage;
    }
    
    @Override
    public RenderedImage getImage() {
        return renderedImage;
    }

    @Override
    public BufferedImage getOriginal() {
        return original;
    }
    
    public MedianPackage(BufferedImage original, RenderedImage renderedImage){
        this.original = original;
        this.renderedImage = renderedImage;
    }

    /**
     * @param original the original to set
     */
    public void setOriginal(BufferedImage original) {
        this.original = original;
    }
}
