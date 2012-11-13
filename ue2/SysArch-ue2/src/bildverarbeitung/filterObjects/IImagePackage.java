/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 *
 * @author green
 */
public interface IImagePackage {
    
    RenderedImage getImage();
    BufferedImage getOriginal();
}
