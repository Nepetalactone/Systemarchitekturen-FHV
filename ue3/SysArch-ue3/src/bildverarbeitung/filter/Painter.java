/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author green
 */
public class Painter extends JComponent implements PropertyChangeListener {
    
    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private IImagePackage imgPack;
    private String name;
    
    
    public Painter(){
        change.addPropertyChangeListener(this);
    }
    
    public Painter(String name, IImagePackage imgPack){
        super();
        this.name = name;
        this.imgPack = imgPack;
        JFrame frame = new JFrame();
        frame.setSize(imgPack.getImage().getWidth()+16,imgPack.getImage().getHeight()+45);
        frame.add(this);
        frame.setVisible(true);
        
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(imgPack instanceof RawPackage){
            RawPackage r = (RawPackage) imgPack;
            drawImage(g2d,imgPack.getImage());
        }else if(imgPack instanceof ROIPackage){
            ROIPackage r = (ROIPackage) imgPack;
            if(r.isShowRectangle()){
                drawImage(g2d,r.getOriginal());
                Rectangle rec = r.getRectangle();
                g2d.setColor(Color.white);
                g2d.drawRect((int)rec.getMinX(), (int)rec.getMinY(), (int)rec.getMaxX(), (int)rec.getMaxY()/2 +5);//kA warum aber as resultierende img isch kleiner als as rechteck ...
            }else{
                drawImage(g2d,r.getImage());
            }
        }else if(imgPack instanceof ThreshPackage){
            ThreshPackage t = (ThreshPackage) imgPack;
            if(t.isShowOriginal()){
                drawImage(g2d,t.getOriginal());
            }else{
                drawImage(g2d,t.getImage());
            }
        }else if(imgPack instanceof MedianPackage){
            MedianPackage m = (MedianPackage) imgPack;
            drawImage(g2d, m.getImage());
        }else if(imgPack instanceof ResultPackage){
            ResultPackage r = (ResultPackage) imgPack;
        }else if(imgPack instanceof ErodePackage){
            ErodePackage e = (ErodePackage) imgPack;
            drawImage(g2d, e.getImage());
        }else if(imgPack instanceof DilatePackage){
            DilatePackage d = (DilatePackage) imgPack;
            drawImage(g2d, d.getImage());
        }else if(imgPack instanceof CentroidPackage){
            CentroidPackage p = (CentroidPackage) imgPack;
            drawImage(g2d, p.getImage());
        }
    }

    private void drawImage(Graphics2D g, RenderedImage img){
        g.drawRenderedImage(img, new AffineTransform());
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        change.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        change.removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.imgPack = (IImagePackage) evt.getNewValue();
        this.name = imgPack.getClass().getName();
        this.repaint();
    }

    public PropertyChangeSupport getChange() {
        return change;
    }

    public void setChange(PropertyChangeSupport change) {
        this.change = change;
    }

    public IImagePackage getImgPack() {
        return imgPack;
    }

    public void setImgPack(IImagePackage imgPack) {
        IImagePackage old = this.imgPack;
        this.imgPack = imgPack;
        change.firePropertyChange("ImagePackage",old,imgPack);
    }
}