/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung.filter;

import framework.filter.Filter;

/**
 *
 * @author Tobias
 */
public class DataSink<in,out> extends Filter<in,out>{
    
    int count = 0;
    
    public DataSink(){
    	super();
    }
    
    @Override
    public boolean filter(in data) {
        count++;
        return true;
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    
}
