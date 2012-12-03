/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import bildverarbeitung.filterObjects.IImagePackage;
import bildverarbeitung.filterObjects.RawPackage;
import bildverarbeitung.filterObjects.helper.ImageFileHelper;
import framework.endpoint.DataSource;
import framework.pipe.IPipe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

/**
 *
 * @author Tobias
 */
public class Source extends DataSource implements PropertyChangeListener, ActionListener{

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
        RawPackage p = (RawPackage) createImagePackage(f);
        change.firePropertyChange("result",this,p);
        return p;
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
    
    private File getChosenFile(JFileChooser chooser) throws Exception{
        JFrame frame = new JFrame();
        chooser.showOpenDialog(frame);
        
        File f = chooser.getSelectedFile();
        if(f == null){
            throw new Exception("Action canceled");
        }
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

    

    
    public void addActionListener(ActionListener l){
    }
    
    public void removeActionListener(ActionListener l){
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            run();
        } catch (Exception ex) {
            Logger.getLogger(Source.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener l){
        change.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        change.removePropertyChangeListener(l);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
}
