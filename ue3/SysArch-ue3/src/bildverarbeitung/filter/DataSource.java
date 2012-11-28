/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.RawPackage;
import framework.filter.ActiveFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FileChooserUI;

/**
 *
 * @author Tobias
 */
public class DataSource<in, out> extends ActiveFilter<in, out> {

    public DataSource() {
    	super();
    }

    public BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
        }
        return img;
    }

    @Override
    public void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
    	JFileChooser chooser = new JFileChooser();
    	chooser.showOpenDialog(new JFrame());
    	chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jar") || f.isDirectory();
            }
            public String getDescription() {
                return "Java Archive file (*.jar)";
            }
    	});
        in data = (in) chooser.getSelectedFile();
        push(data);
    }

    @Override
    public boolean filter(in data) {
        BufferedImage buffer = getImage((String) data);

        if (buffer != null) {
            //0, 50, 447, 55
            RawPackage ri = new RawPackage(buffer);
            super.result = (out) ri;

            return true;
        }
        return false;
    }
}
