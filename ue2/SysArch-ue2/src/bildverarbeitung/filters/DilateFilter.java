/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import bildverarbeitung.filterObjects.DilatePackage;
import bildverarbeitung.filterObjects.ErodePackage;
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
public class DilateFilter extends Filter {

    @Override
    public boolean filter(Object data) {
        KernelJAI k = new KernelJAI(7, 7, new float[]{
                    0, 0, 0, 1, 0, 0, 0,
                    0, 0, 1, 1, 1, 0, 0,
                    0, 1, 1, 1, 1, 1, 0,
                    1, 1, 1, 1, 1, 1, 1,
                    0, 1, 1, 1, 1, 1, 0,
                    0, 0, 1, 1, 1, 0, 0,
                    0, 0, 0, 1, 0, 0, 0,});

        Journal.getInstance().setDilateKernel(k);

        ParameterBlock pb = new ParameterBlock();
        ErodePackage eroPack = (ErodePackage) data;

        pb.addSource(eroPack.getImage());
        pb.add(k);

        RenderedImage img = JAI.create("Dilate", pb);
        DilatePackage dilPack = new DilatePackage(eroPack.getOriginal(), img);
        Painter p = new Painter("Dilate", dilPack);
        result = dilPack;
        return true;
    }
}
