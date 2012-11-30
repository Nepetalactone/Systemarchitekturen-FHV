/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import framework.endpoint.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Tobias
 */
public class Source<BufferedImage> extends DataSource<BufferedImage> {

    private final String[] ext = new String[]{"jpeg","jpg","png","bmp"};
    private ArrayList<String> extensions;
    
    
    public Source() {
    	super();
        initExtensionList();
    }
    
    public Source(boolean isActive){
        super.isActive = isActive;
        initExtensionList();
        
    }
    
    private void initExtensionList(){
        this.extensions = new ArrayList<String>();
        for(String s: ext){
            extensions.add(s);
        }
    }

    @Override
    public BufferedImage readSource() throws Exception {
        JFileChooser chooser = new JFileChooser();
    	chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                String ext = getExtension(f);
                if(f.isDirectory()){
                    return true;
                }else if(ext != null){
                    if(ext.equals("jpeg") || ext.equals("jpg") || ext.equals("png") || ext.equals("bmp"))
                    return true;
                }
                return false;
            }
            public String getDescription() {
                return "*.jpeg, *.jpg, *.png, *.bmp";
            }
    	});
        
        JFrame frame = new JFrame();
        BufferedImage b = null;
        chooser.showOpenDialog(frame);
        
        File f = chooser.getSelectedFile();
        while(!extensions.contains(getExtension(f))){
            String errorMsg = "Valid file extensions are: ";
            for(String s: extensions){
                errorMsg = errorMsg + s + " ";
            }
            JOptionPane.showMessageDialog(frame,errorMsg);
            chooser.showOpenDialog(frame);
            f = chooser.getSelectedFile();
        }
        
        b = (BufferedImage) ImageIO.read(f);
        return b;
    }
    
    
    public static void main(String[]args){
        Source s = new Source();
        try {
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
