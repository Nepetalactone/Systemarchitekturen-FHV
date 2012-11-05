package pipe;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;

import filter.IFilter;

public abstract class Pipe<T> implements IPipe<T> {
	private Collection<IFilter> inputFilters;
	private Collection<IFilter> outputFilters;
	private Collection<T> buffer;

	public Pipe() {
		inputFilters = new LinkedList<IFilter>();
		outputFilters = new LinkedList<IFilter>();
		buffer = new LinkedList<T>();
	}
	
	/**
	 * Gibt Daten an die Output Filter weiter
	 */
	public void push(T data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		for (IFilter outputFilter : outputFilters) {
			outputFilter.push(data);
		}
	}
	
	/**
	 * Holt Daten vom Input Filter in die Pipe
	 */
	public T pull() {
		T data = null;
		for (IFilter inputFilter : inputFilters) {
			data = (T) inputFilter.pull();
			buffer.add(data);
		}
		return data;
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

