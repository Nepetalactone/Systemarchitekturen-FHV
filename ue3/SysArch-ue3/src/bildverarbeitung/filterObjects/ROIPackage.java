/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIPackage implements IImagePackage  {
    
    
    private PlanarImage img;
    private BufferedImage original;
    private Rectangle rectangle;
    private boolean showRectangle;

    public ROIPackage(PlanarImage img, BufferedImage original, Rectangle rectangle, boolean showRectangle) {
        this.img = img;
        this.original = original;
        this.rectangle = rectangle;
        this.showRectangle = showRectangle;
    }


    public PlanarImage getImg() {
        return img;
    }

    public void setImg(PlanarImage img) {
        this.img = img;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public RenderedImage getImage() {
        return img;
    }

    public BufferedImage getOriginal() {
        return original;
    }

    public void setOriginal(BufferedImage original) {
        this.original = original;
    }

    public boolean isShowRectangle() {
        return showRectangle;
    }

    public void setShowRectangle(boolean showRectangle) {
        this.showRectangle = showRectangle;
    }

    @Override
    public void setImage(RenderedImage img) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
