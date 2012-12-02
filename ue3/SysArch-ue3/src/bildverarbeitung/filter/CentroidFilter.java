/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.CentroidPackage;
import bildverarbeitung.filterObjects.DilatePackage;
import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.filter.Filter;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class CentroidFilter extends Filter implements Serializable, PropertyChangeListener{
	
        private PropertyChangeSupport change = new PropertyChangeSupport(this);
        IImagePackage workingCopy = null;
        
	public CentroidFilter(){
		super();
                change.addPropertyChangeListener(this);
	}
        
        public void actionPerformed(java.awt.event.ActionEvent evt){
            System.out.println("yeah");
        }

    @Override
    public boolean filter(Object data) {
        IImagePackage imgPackage = (IImagePackage) data;
        BufferedImage b = ImageFileHelper.convertRenderedImageToBufferedImage(imgPackage.getImage());
        Point center = getCenter(b);
        this.workingCopy = new CentroidPackage(imgPackage.getOriginal(),b,center);
        result = workingCopy;
        return true;
        
    }
    
    
    private Point getCenter(BufferedImage b){
        int sumX = 0;
        int sumY = 0;
        int counter = 0;
        
        for(int y = 0; y < b.getHeight(); y++){
            for(int x = 0; x < b.getWidth(); x++){
                int clr = b.getRGB(x, y);
                int white = Color.white.getRGB();
                if(clr == white){
                    sumX += x;
                    sumY += y;
                    counter++;
                }
            }
        }
        
        int xx = Math.round(sumX / counter);
        int yy = Math.round(sumY / counter);
        return new Point(xx,yy);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        change.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        change.removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            if(workingCopy != null){
                push(workingCopy);
            }
        } catch (Exception ex) {
            Logger.getLogger(CentroidFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
