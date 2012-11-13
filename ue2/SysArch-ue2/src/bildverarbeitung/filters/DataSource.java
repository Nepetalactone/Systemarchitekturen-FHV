/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.RawImage;
import bildverarbeitung.pipes.ImagePipe;
import filter.ActiveFilter;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pipe.IPipe;

/**
 *
 * @author Tobias
 */
public class DataSource<in,out> extends ActiveFilter<in,out> {

    public DataSource() {
        
    }
    
    public BufferedImage getImage(String path){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(path));
        }catch(IOException e){
        }
        return img;
    }

    @Override
    public void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        in data = (in) "res/loetstellen.jpg";
        push(data);
    }


    @Override
    public boolean filter(in data) {
        BufferedImage buffer = getImage((String)data);
        if(buffer != null){
            super.result = (out) new RawImage(buffer,new Point(11,11), new Point(22,22));
            return true;
        }
        return false;
    }
}
