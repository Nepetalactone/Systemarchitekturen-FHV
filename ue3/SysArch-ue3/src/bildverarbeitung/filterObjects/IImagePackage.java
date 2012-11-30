/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

/**
 *
 * @author green
 */
public interface IImagePackage {
    
    RenderedImage getImage();
    BufferedImage getOriginal();
    void setImage(RenderedImage img);
    void setOriginal(BufferedImage img);
}
