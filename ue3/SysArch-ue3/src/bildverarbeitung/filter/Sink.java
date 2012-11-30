/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.endpoint.DataSink;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Tobias
 */
public class Sink extends DataSink{
    
    private int i;
    
    public Sink(){
    	super();
        i = 0;
    }
    public Sink(boolean isActive){
        super(isActive);
        i = 0;
    }

    @Override
    public void saveData(Object data) {
        if(data instanceof IImagePackage){
            try {
                IImagePackage p = (IImagePackage) data;
                ImageFileHelper.saveImageToFile("FilteredImage_"+i+".png", (BufferedImage)p.getImage());
                i++;
            } catch (IOException ex) {
                Logger.getLogger(Sink.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    


    
}
