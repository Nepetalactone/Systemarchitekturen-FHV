package pipe;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import filter.IFilter;

public abstract class Pipe<T> implements IPipe<T> {
	private Collection<IFilter> inputFilters;
	private Collection<IFilter> outputFilters;
	private Collection<T> buffer;

	public Pipe(Collection t) {
		buffer = t;
	}
	
	public void push(T data){
		buffer.add(data);
	}
	
	public T pull() {
		for (T element : buffer) {
			for (IFilter outputFilter : outputFilters) {
				
			}
		}
		for (IFilter outputFilter : outputFilters) {
			for (T element : buffer){
				buffer.remove(element);
				try {
					outputFilter.push(element);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return element;
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

