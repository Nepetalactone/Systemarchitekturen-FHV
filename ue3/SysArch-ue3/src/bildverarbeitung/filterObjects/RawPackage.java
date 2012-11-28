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
public class RawPackage implements IImagePackage {
    
    private BufferedImage img;
    
    public RawPackage(BufferedImage img) {
        this.img = img;
    }
    
    
    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }

    @Override
    public RenderedImage getImage() {
        return img;
    }

    @Override
    public BufferedImage getOriginal() {
        return this.img;
    }


	@Override
	public void setImage(RenderedImage img) {
		this.img = (BufferedImage) img;
	}


	@Override
	public void setOriginal(BufferedImage img) {
		this.img = img;
	}
}
