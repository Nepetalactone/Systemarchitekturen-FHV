/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ThreshPackage  implements IImagePackage {

    
    private RenderedImage image;
    private BufferedImage original;
    private boolean showOriginal;

    public ThreshPackage(RenderedImage image,BufferedImage original, boolean showOriginal) {
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
    
}
