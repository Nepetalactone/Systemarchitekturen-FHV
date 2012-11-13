/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class ROIImage {
    
    
    private PlanarImage img;

    public ROIImage(PlanarImage img) {
        this.img = img;
    }

    public PlanarImage getImg() {
        return img;
    }

    public void setImg(PlanarImage img) {
        this.img = img;
    }
    
}
