/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bildverarbeitung.filters.DataSink;
import bildverarbeitung.filters.DataSource;
import bildverarbeitung.filters.MedianFilter;
import bildverarbeitung.filters.ROIFilter;
import bildverarbeitung.filters.ThresholdFilter;
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
        
        
        DataSink sink = new DataSink();
        p4.addInputFilter(median);
        p4.addOutputFilter(sink);
        
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
