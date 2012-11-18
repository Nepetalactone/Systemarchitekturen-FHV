/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Rectangle;
import javax.media.jai.KernelJAI;

/**
 *
 * @author Tobias
 */
public class Journal {

    private static Journal instance = null;

    private Journal() {
    }

    public static Journal getInstance() {
        if (instance == null) {
            instance = new Journal();
        }
        return instance;
    }
    //Threshold variables
    private double[] thresholdLow;
    private double[] thresholdHigh;
    private double[] thresholdConstants;
    //Median variables
    //Erode variables  
    private KernelJAI erodeKernel;
    //Dilate variables
    private KernelJAI dilateKernel;
    //ROI2 variables
    private Rectangle[] roi2Rectangles;
    //DataSink variables
    private int sinkCount;

    public void setDilateKernel(KernelJAI dilateKernel) {
        this.dilateKernel = dilateKernel;
    }

    public void setErodeKernel(KernelJAI erodeKernel) {
        this.erodeKernel = erodeKernel;
    }

    public void setRoi2Rectangles(Rectangle[] roi2Rectangles) {
        this.roi2Rectangles = roi2Rectangles;
    }

    public void setThresholdConstants(double[] thresholdConstants) {
        this.thresholdConstants = thresholdConstants;
    }

    public void setThresholdHigh(double[] thresholdHigh) {
        this.thresholdHigh = thresholdHigh;
    }

    public void setThresholdLow(double[] thresholdLow) {
        this.thresholdLow = thresholdLow;
    }

    public void setSinkCount(int count) {
        this.sinkCount = count;
    }

    public void print() {
        System.out.println("Threshold low: " + thresholdLow[0] + ", " + thresholdLow[1] + ", " + thresholdLow[2]);
        System.out.println("Threshold low: " + thresholdHigh[0] + ", " + thresholdHigh[1] + ", " + thresholdHigh[2]);
        System.out.println("Threshold low: " + thresholdConstants[0] + ", " + thresholdConstants[1] + ", " + thresholdConstants[2]);
        System.out.println("Erode KernelJAI: \n");
        
        int i = 0;
        int j = 0;
        
        while (i < erodeKernel.getHeight()){
            while (j < erodeKernel.getWidth()){
                System.out.print(erodeKernel.getElement(i, j) + ", ");
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }
        
        i = 0;
        j = 0;
        
        System.out.println();
        while (i < dilateKernel.getHeight()){
            while (j < dilateKernel.getWidth()){
                System.out.print(dilateKernel.getElement(i, j) + ", ");
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }
        
        System.out.println("Rectangles\n");
        for (Rectangle rect : roi2Rectangles){
            System.out.println(" X: " + rect.x + " Y: " + rect.y + " Width: " + rect.width + " Heigth: " + rect.height);
        }
        
        System.out.println("Ballcount: " + sinkCount);
    }
}
