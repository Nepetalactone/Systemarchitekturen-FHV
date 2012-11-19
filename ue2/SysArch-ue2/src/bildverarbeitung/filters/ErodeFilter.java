/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.ErodePackage;
import bildverarbeitung.filterObjects.MedianPackage;
import darstellung.Painter;
import filter.Filter;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import test.Journal;

/**
 *
 * @author Tobias
 */
public class ErodeFilter extends Filter {

    @Override
    public boolean filter(Object data) {
        KernelJAI k = new KernelJAI(11, 11, new float[]{
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                    0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,});

        Journal.getInstance().setErodeKernel(k);

        ParameterBlock pb = new ParameterBlock();
        MedianPackage medPack = (MedianPackage) data;

        pb.addSource(medPack.getImage());
        pb.add(k);

        RenderedImage img = JAI.create("Erode", pb);
        ErodePackage eroPack = new ErodePackage(medPack.getOriginal(), img);
        Painter p = new Painter("Erode", eroPack);

        result = eroPack;

        return true;
    }
}
