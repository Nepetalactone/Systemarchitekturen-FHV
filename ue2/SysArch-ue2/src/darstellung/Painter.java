/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package darstellung;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.MedianImage;
import bildverarbeitung.filterObjects.ROIImage;
import bildverarbeitung.filterObjects.RawImage;
import bildverarbeitung.filterObjects.ResultImage;
import bildverarbeitung.filterObjects.ThreshImage;
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
                if(imgPack instanceof RawImage){
                    RawImage r = (RawImage) imgPack;
                    drawImage(g2d,imgPack.getImage());
                }else if(imgPack instanceof ROIImage){
                    ROIImage r = (ROIImage) imgPack;
                    if(r.isShowRectangle()){
                        drawImage(g2d,r.getOriginal());
                        Rectangle rec = r.getRectangle();
                        g2d.setColor(Color.white);
                        g2d.drawRect((int)rec.getMinX(), (int)rec.getMinY(), (int)rec.getMaxX(), (int)rec.getMaxY());
                    }else{
                        drawImage(g2d,r.getImage());
                    }
                }else if(imgPack instanceof ThreshImage){
                    ThreshImage t = (ThreshImage) imgPack;
                    drawImage(g2d,t.getImage());
                }else if(imgPack instanceof MedianImage){
                    MedianImage m = (MedianImage) imgPack;
                }else if(imgPack instanceof ResultImage){
                    ResultImage r = (ResultImage) imgPack;
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
