/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bildverarbeitung.filterObjects.CentroidFilter;
import bildverarbeitung.filters.*;
import bildverarbeitung.pipes.ImagePipe;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author green
 */
public class test {
    
    
    public static void main(String[] args){
        
        ImagePipe p1 = new ImagePipe();
        ImagePipe p2 = new ImagePipe();
        ImagePipe p3 = new ImagePipe();
        ImagePipe p4 = new ImagePipe();
        ImagePipe p5 = new ImagePipe();
        ImagePipe p6 = new ImagePipe();
        ImagePipe p7 = new ImagePipe();
        
        DataSource source = new DataSource();
        source.addOutputPipe(p1);
        ROIFilter roi = new ROIFilter();
        roi.addOutputPipe(p2);
        p1.addInputFilter(source);
        p1.addOutputFilter(roi);
        
        ThresholdFilter thresh = new ThresholdFilter();
        thresh.addOutputPipe(p3);
        p2.addInputFilter(roi);
        p2.addOutputFilter(thresh);
        
        MedianFilter median = new MedianFilter();
        median.addOutputPipe(p4);
        p3.addInputFilter(thresh);
        p3.addOutputFilter(median);
        
        ErodeFilter erode = new ErodeFilter();
        erode.addOutputPipe(p5);
        p4.addInputFilter(median);
        p4.addOutputFilter(erode);
        
        DilateFilter dilate = new DilateFilter();
        dilate.addOutputPipe(p6);
        p5.addInputFilter(erode);
        p5.addOutputFilter(dilate);
        
        CentroidFilter centroid = new CentroidFilter();
        centroid.addOutputPipe(p7);
        p6.addInputFilter(dilate);
        p6.addOutputFilter(centroid);
        
        DataSink sink = new DataSink();
        p7.addInputFilter(centroid);
        p7.addOutputFilter(sink);
        
        try {
            source.run();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
