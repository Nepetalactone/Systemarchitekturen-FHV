package filter;

import java.util.Collection;

import pipe.IPipe;

public abstract class Filter<in,out> implements IFilter<in, out>{

	
	private Collection<IPipe> inputPipes;
	private Collection<IPipe> outputPipes;
	private out result;
	
	public Filter(){
		
	}
	
	
	@Override
	public void push(in data){
		if(filter(data)){
			//send copy of data
		}
	}
	
	@Override
	public out pull() {
		return null;
	}
	
	private boolean filter(in data){
		
		
		
		
		return false;
	}
}
