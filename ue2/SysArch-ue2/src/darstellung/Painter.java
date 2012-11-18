/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package darstellung;

import bildverarbeitung.filterObjects.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author green
 */
public class Painter extends JFrame {
    
    private class PainterCanvas extends JComponent {
        private IImagePackage imgPack;
        
            public PainterCanvas(IImagePackage imgPack){
                this.imgPack = imgPack;
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
    }
    
    private String title;
    
    
    public Painter(String title,IImagePackage imgPack){
        super();
        setTitle(title);
        setSize(imgPack.getOriginal().getWidth(),imgPack.getOriginal().getHeight());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new PainterCanvas(imgPack));
        setVisible(true);
    }
}
