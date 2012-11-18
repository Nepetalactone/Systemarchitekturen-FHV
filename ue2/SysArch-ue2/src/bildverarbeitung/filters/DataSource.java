/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.RawPackage;
import darstellung.Painter;
import filter.ActiveFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;

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
            //0, 50, 447, 55
            RawPackage ri = new RawPackage(buffer);
            super.result = (out) ri;
            
            Painter p = new Painter("Source",ri);
            
            
            return true;
        }
        return false;
    }
}
