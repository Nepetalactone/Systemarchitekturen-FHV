/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.RawPackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.endpoint.DataSource;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Tobias
 */
public class Source extends DataSource implements MouseListener{

    private PropertyChangeSupport change;
    
    private final String[] ext = new String[]{"jpeg","jpg","png","bmp"};
    private ArrayList<String> extensions;
    
    
    public Source() {
    	super();
        change = new PropertyChangeSupport(this);
        initExtensionList();
    }
    
    public Source(boolean isActive){
        super(isActive);
        change = new PropertyChangeSupport(this);
        initExtensionList();
        
    }
    
    private void initExtensionList(){
        this.extensions = new ArrayList<String>();
        for(String s: ext){
            extensions.add(s);
        }
    }

    @Override
    public RawPackage readSource() throws Exception {
        JFileChooser chooser = new JFileChooser();
        setFileSelectionFilter(chooser);
        File f = getChosenFile(chooser);
//        Painter p = new Painter("asd",createImagePackage(f));
        return (RawPackage) createImagePackage(f);
    }
    
    
    private void setFileSelectionFilter(JFileChooser chooser){
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                String ext = ImageFileHelper.getExtension(f);
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
    }
    
    private File getChosenFile(JFileChooser chooser){
        JFrame frame = new JFrame();
        chooser.showOpenDialog(frame);
        
        File f = chooser.getSelectedFile();
        while(!extensions.contains(ImageFileHelper.getExtension(f))){
            String errorMsg = "Valid file extensions are: ";
            for(String s: extensions){
                errorMsg = errorMsg + s + " ";
            }
            JOptionPane.showMessageDialog(frame,errorMsg);
            chooser.showOpenDialog(frame);
        }
        return f;
    }
    
    private IImagePackage createImagePackage(File f) throws IOException{
        BufferedImage b = ImageFileHelper.loadImageFromFile(f);
        return new RawPackage(b);
    }
    
//    public static void main(String[] args){
//        Source s = new Source();
//        try {
//            s.run();
//        } catch (Exception ex) {
//            Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void addMouseListener(MouseListener l){
    }
    
    public void removeMouseListener(MouseListener l){
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            run();
        } catch (Exception ex) {
            Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
