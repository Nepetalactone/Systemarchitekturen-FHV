/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filters;

import filter.Filter;
import test.Journal;

/**
 *
 * @author Tobias
 */
public class DataSink<in,out> extends Filter<in,out>{
    
    int count = 0;
    
    @Override
    public boolean filter(in data) {
        count++;
        Journal.getInstance().setSinkCount(count);
        return true;
    }
}
