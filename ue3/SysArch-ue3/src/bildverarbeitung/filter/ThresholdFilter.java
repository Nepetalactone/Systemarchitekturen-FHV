/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.ROIPackage;
import bildverarbeitung.filterObjects.ThreshPackage;
import framework.filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;

/**
 *
 * @author Tobias
 */
public class ThresholdFilter<in, out> extends Filter<in, out> {

    public ThresholdFilter() {
        super();
    }

    @Override
    public boolean filter(in data) {
        //0,30,255
        //0,254,0
        //
        ROIPackage roiImage = (ROIPackage) data;
        double[] low = new double[]{0, 0, 0};
        double[] high = new double[]{33, 33, 33};
        double[] constants = new double[]{255, 255, 255};

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(roiImage.getImage());
        pb.add(low);
        pb.add(high);
        pb.add(constants);
        RenderedImage thresh = JAI.create("threshold", pb);
        ThreshPackage threshPack = new ThreshPackage(thresh, roiImage.getOriginal(), false);
        result = (out) threshPack;
        return true;

    }
}
