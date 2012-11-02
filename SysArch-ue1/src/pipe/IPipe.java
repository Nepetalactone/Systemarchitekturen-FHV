package pipe;

import java.util.Collection;
import filter.IFilter;

public interface IPipe<T> {
	void push(T data);
	T pull();
	Collection<IFilter> getInputFilters();
	
	void addOutputFilter(IFilter filter);
	void addInputFilter(IFilter filter);
	void removeOutputFilter(IFilter filter);
	void removeInputFilter(IFilter filter);
}
