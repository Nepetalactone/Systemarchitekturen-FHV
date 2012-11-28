package framework.filter;

import java.lang.reflect.InvocationTargetException;

import framework.pipe.IPipe;

public interface IFilter<in,out> {
	void push(in data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException;
	out pull();
	
	void addOutputPipe(IPipe pipe);
	void addInputPipe(IPipe pipe);
	void removeOutputPipe(IPipe pipe);
	void removeInputPipe(IPipe pipe);
	boolean filter(in data);
}
