package filter;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;

import pipe.IPipe;

public abstract class Filter<in,out> implements IFilter<in, out>{
	protected Collection<IPipe> inputPipes;
	protected Collection<IPipe> outputPipes;
	protected out result;
	
	public Filter(){
		inputPipes = new LinkedList<IPipe>();
		outputPipes = new LinkedList<IPipe>();
	}
	
	public void addInputPipe(IPipe pipe) {
		inputPipes.add(pipe);
	}
	
	public void addOutputPipe(IPipe pipe) {
		outputPipes.add(pipe);
	}
	
	public void removeInputPipe(IPipe pipe){
		inputPipes.remove(pipe);
	}
	
	public void removeOutputPipe(IPipe pipe){
		outputPipes.remove(pipe);
	}
	
	public boolean isFinished() {
		boolean finished = false;
		for (IPipe pipe : inputPipes) {
			in temp = (in) pipe.pull();
			
			if (!filter(temp)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gibt eine Deepcopy an die output Pipes weiter
	 */
	@Override
	public void push(in data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		
		if(filter(data)){
			//send copy of data
			for (IPipe pipe : outputPipes) {
				//pipe.push(getDeepCopy());
				pipe.push(result);
			}
		}
	}
	
	/**
	 * Holt Text von Pipe in den Filter
	 */
	@Override
	public out pull() {
		while (!isFinished()) {
			pull();
		}
		return result;
	}
	
//	public out getDeepCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
//		Constructor constr = result.getClass().getConstructor(result.getClass());
//		return (out) constr.newInstance(result);
//	}
	
	public boolean filter(in data) {
		return true;
	}
}
