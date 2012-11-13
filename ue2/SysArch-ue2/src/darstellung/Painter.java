/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package darstellung;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.MedianPackage;
import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.RawPackage;
import bildverarbeitung.filterObjects.ResultPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.media.jai.widget.ImageCanvas;
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
                        g2d.drawRect((int)rec.getMinX(), (int)rec.getMinY(), (int)rec.getMaxX(), (int)rec.getMaxY());
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
                }else if(imgPack instanceof ResultPackage){
                    ResultPackage r = (ResultPackage) imgPack;
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
