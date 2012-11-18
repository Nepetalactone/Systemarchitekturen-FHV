/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import filter.Filter;

/**
 *
 * @author Tobias
 */
public class DataSink<in,out> extends Filter<in,out>{

    @Override
    public boolean filter(in data) {
        return true;
    }
    
}
