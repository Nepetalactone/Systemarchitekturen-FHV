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
public class ThreshPackage  implements IImagePackage {

    
    private RenderedImage image;
    private BufferedImage original;
    private boolean showOriginal;

    public ThreshPackage(BufferedImage original, RenderedImage image, boolean showOriginal) {
        this.image = image;
        this.original = original;
        this.showOriginal = showOriginal;
    }
    
    @Override
    public RenderedImage getImage() {
        return image;
    }

    @Override
    public BufferedImage getOriginal() {
        return original;
    }

    public boolean isShowOriginal() {
        return showOriginal;
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
