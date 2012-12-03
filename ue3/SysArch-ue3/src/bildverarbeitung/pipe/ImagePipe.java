/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.pipe;

import bildverarbeitung.filter.Source;
import framework.pipe.Pipe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author green
 */
public class ImagePipe extends Pipe implements ActionListener,PropertyChangeListener{

    private PropertyChangeSupport change;
     
    public ImagePipe(){
        super();
        change = new PropertyChangeSupport(this);
    }
    
    public void addActionListener(ActionListener l){
    }
    
    public void removeActionListener(ActionListener l){
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        change.notifyAll();
    }

    public void addPropertyChangeListener(PropertyChangeListener l){
        change.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        change.removePropertyChangeListener(l);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("notifyFilter");
        change.notifyAll();
    }
    
}
