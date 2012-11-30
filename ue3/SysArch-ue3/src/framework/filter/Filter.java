package framework.filter;

import java.util.ArrayList;
import java.util.Collection;

import framework.pipe.IPipe;


public abstract class Filter<in,out> implements IFilter<in, out> {
	protected Collection<IPipe> inputPipes;
	protected Collection<IPipe> outputPipes;
	protected Collection<in> buffer;
	protected out result;
	
	public Filter(){
		inputPipes = new ArrayList<IPipe>();
		outputPipes = new ArrayList<IPipe>();
	}
	
    @Override
	public void addInputPipe(IPipe pipe) {
		inputPipes.add(pipe);
	}
	
    @Override
	public void addOutputPipe(IPipe pipe) {
		outputPipes.add(pipe);
	}
	
    @Override
	public void removeInputPipe(IPipe pipe){
		inputPipes.remove(pipe);
	}
	
    @Override
	public void removeOutputPipe(IPipe pipe){
		outputPipes.remove(pipe);
	}
	
	
	/**
	 * Gibt eine Deepcopy an die output Pipes weiter
	 */
	@Override
	public void push(in data) throws Exception {
		if(filter(data)){
			for(IPipe p: outputPipes){
				//make deep copy
				p.push(result);
			}
		}
	}
	
	/**
	 * Holt Text von Pipe in den Filter
	 */
	@Override
	public out pull() throws Exception {
		out data = result;
		if(data == null){
			for(IPipe p: inputPipes){
				if(filter((in)p.pull())){
					data = result;
					result = null;
					return data;
				}
			}
		}
		result = null;
		return data;
	}
	
//	public out getDeepCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
//		Constructor constr = result.getClass().getConstructor(result.getClass());
//		return (out) constr.newInstance(result);
//	}
	
    @Override
    public abstract boolean filter(in data) throws Exception;
}
