/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import framework.endpoint.DataSink;
import bildverarbeitung.filterObjects.IImagePackage;

/**
 *
 * @author Tobias
 */
public class Sink extends DataSink{
    
    
    public Sink(){
    	super();
    }
    public Sink(boolean isActive){
        super(isActive);
    }

    @Override
    public void saveData(Object data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    


    
}
