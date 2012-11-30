package framework.pipe;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;

import framework.filter.IFilter;


public class Pipe<T> implements IPipe<T> {
	private Collection<IFilter> inputFilters;
	private Collection<IFilter> outputFilters;

	public Pipe() {
		inputFilters = new LinkedList<IFilter>();
		outputFilters = new LinkedList<IFilter>();
	}
	
	/**
	 * Gibt Daten an die Output Filter weiter
	 */
	public void push(T data) throws Exception{
		for (IFilter outputFilter : outputFilters) {
			outputFilter.push(data);
		}
	}
	
	/**
	 * Holt Daten vom Input Filter in die Pipe
	 */
	public T pull() throws Exception {
		T data = null;
		for(IFilter f : inputFilters){
			data = (T) f.pull();
			if(data != null){
				return data;
			}
		}
		return null;
	}
	
	
	public void addInputFilter(IFilter filter) {
		inputFilters.add(filter);
	}
	
	public void addOutputFilter(IFilter filter) {
		outputFilters.add(filter);
	}
	
	public void removeInputFilter(IFilter filter){
		inputFilters.remove(filter);
	}
	
	public void removeOutputFilter(IFilter filter){
		outputFilters.remove(filter);
	}

}	

