package framework.endpoint;

import java.util.Collection;

import framework.pipe.IPipe;

public abstract class DataSink<T>{
	
	public static final boolean ACTIVE_SINK = true;
	public static final boolean PASSIVE_SINK = false;
	
	protected Collection<IPipe> inputPipes;
	protected boolean isActive;
	protected Collection<T> finishedObjects;
	
	
	public DataSink(boolean isActive){
		this.isActive = isActive;
	}
	
	
	public void run() throws Exception{
		if(isActive){
			for(IPipe p: inputPipes){
				T temp = (T) p.pull();
				if(temp != null){
					finishedObjects.add(temp);
				}
			}
		}
	}
	
	
	public abstract void saveData(T data);
	
	
	public void push(T data){
		finishedObjects.add(data);
		saveData(data);
	}
	
	public void addInputPipe(IPipe pipe){
		this.inputPipes.add(pipe);
	}
	public void removeInputPipe(IPipe pipe){
		this.inputPipes.remove(pipe);
	}
}
