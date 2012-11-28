package framework.pipe;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import framework.filter.IFilter;


public interface IPipe<T> {
	void push(T data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException;
	T pull();
	
	void addOutputFilter(IFilter filter);
	void addInputFilter(IFilter filter);
	void removeOutputFilter(IFilter filter);
	void removeInputFilter(IFilter filter);
}
