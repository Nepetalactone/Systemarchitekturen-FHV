package framework.filter;

import java.lang.reflect.InvocationTargetException;

import framework.pipe.IPipe;
import java.io.Serializable;

 public interface IFilter<in,out> extends Serializable {
	void push(in data) throws Exception;
	out pull() throws Exception;
	
	void addOutputPipe(IPipe pipe);
	void addInputPipe(IPipe pipe);
	void removeOutputPipe(IPipe pipe);
	void removeInputPipe(IPipe pipe);
	boolean filter(in data) throws Exception;
}
