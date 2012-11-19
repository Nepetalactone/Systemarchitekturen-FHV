/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bildverarbeitung.filters.CentroidFilter;
import bildverarbeitung.filters.*;
import bildverarbeitung.pipes.ImagePipe;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author green
 */
public class test {

    public static void main(String[] args) {

        ImagePipe pSourceToRoi1 = new ImagePipe();
        ImagePipe pRoi1ToThresh = new ImagePipe();
        ImagePipe pThreshToMedian = new ImagePipe();
        ImagePipe pMedianToErode = new ImagePipe();
        ImagePipe pErodeToDilate = new ImagePipe();
        ImagePipe pDilateToRoi2 = new ImagePipe();
        ImagePipe pRoi2ToCentroid = new ImagePipe();
        ImagePipe pCentroidToSink = new ImagePipe();

        DataSource source = new DataSource();
        source.addOutputPipe(pSourceToRoi1);
        ROIFilter roi = new ROIFilter(new Rectangle(0, 50, 447, 55));
        roi.addOutputPipe(pRoi1ToThresh);
        pSourceToRoi1.addInputFilter(source);
        pSourceToRoi1.addOutputFilter(roi);

        ThresholdFilter thresh = new ThresholdFilter();
        thresh.addOutputPipe(pThreshToMedian);
        pRoi1ToThresh.addInputFilter(roi);
        pRoi1ToThresh.addOutputFilter(thresh);

        MedianFilter median = new MedianFilter();
        median.addOutputPipe(pMedianToErode);
        pThreshToMedian.addInputFilter(thresh);
        pThreshToMedian.addOutputFilter(median);

        ErodeFilter erode = new ErodeFilter();
        erode.addOutputPipe(pErodeToDilate);
        pMedianToErode.addInputFilter(median);
        pMedianToErode.addOutputFilter(erode);

        DilateFilter dilate = new DilateFilter();
        dilate.addOutputPipe(pDilateToRoi2);
        pErodeToDilate.addInputFilter(erode);
        pErodeToDilate.addOutputFilter(dilate);

        ROIFilter2 roi2 = new ROIFilter2();
        roi2.addOutputPipe(pRoi2ToCentroid);
        pDilateToRoi2.addInputFilter(dilate);
        pDilateToRoi2.addOutputFilter(roi2);
        
        CentroidFilter centroid = new CentroidFilter();
        centroid.addOutputPipe(pCentroidToSink);
        pRoi2ToCentroid.addInputFilter(roi2);
        pRoi2ToCentroid.addOutputFilter(centroid);

        DataSink sink = new DataSink();
        pCentroidToSink.addInputFilter(centroid);
        pCentroidToSink.addOutputFilter(sink);

        try {
            source.run();
            Journal.getInstance().print();
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
