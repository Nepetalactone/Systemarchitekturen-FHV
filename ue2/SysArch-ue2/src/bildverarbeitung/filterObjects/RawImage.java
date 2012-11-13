/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filterObjects;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tobias
 */
public class RawImage {
    
    private BufferedImage img;
    private Point pointA;
    private Point pointB;
    
    public RawImage(BufferedImage img, Point a, Point b){
        this.img = img;
        this.pointA = a;
        this.pointB = b;
    }
    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }
    
}
