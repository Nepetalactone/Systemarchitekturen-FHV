/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import filter.ActiveFilter;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Tobias
 */
public class DataSource<in,out> extends ActiveFilter<in,out> {

    public DataSource() {
        Rectangle rectangle = new Rectangle();
        BufferedImage buffer = getImage("res/img/loetstellen.jpg");
        PlanarImage image = PlanarImage.wrapRenderedImage(buffer);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(rectangle, image.getColorModel()));
    }
    
    public BufferedImage getImage(String path){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(path));
        }catch(IOException e){
        }
        return img;
    }
}
