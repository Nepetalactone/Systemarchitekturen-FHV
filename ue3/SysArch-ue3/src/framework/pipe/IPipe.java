package framework.pipe;


import framework.filter.IFilter;
import java.io.Serializable;


public interface IPipe<T> extends Serializable{
	void push(T data) throws Exception;
	T pull()throws Exception;
	
	void addOutputFilter(IFilter filter);
	void addInputFilter(IFilter filter);
	void removeOutputFilter(IFilter filter);
	void removeInputFilter(IFilter filter);
}
