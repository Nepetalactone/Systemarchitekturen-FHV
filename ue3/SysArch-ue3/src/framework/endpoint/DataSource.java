package framework.endpoint;

import java.util.Collection;

import framework.pipe.IPipe;
import java.util.ArrayList;

public abstract class DataSource<T>{
	
	public static final boolean ACTIVE_SOURCE = true;
	public static final boolean PASSIVE_SOURCE = false;
	
	protected Collection<IPipe> outputPipes;
	protected boolean isActive;
	protected T data;
	
	
        public DataSource(){
            initCollections();
        }
        
	public DataSource(boolean isActive){
		this.isActive = isActive;
                initCollections();
	}
	
	private void initCollections(){
            outputPipes = new ArrayList<IPipe>();
        }
        
	public void run() throws Exception{
		data = readSource();
		if(data == null){
                    throw new Exception("Could not load File");
                }
                if(isActive){
			for(IPipe p: outputPipes){
				p.push(data);
			}
		}
	}
	
	public abstract T readSource() throws Exception;
	
	public T pull(){
		return data;
	}
	
	public void addOutputPipe(IPipe pipe){
		this.outputPipes.add(pipe);
	}
	public void removeOutputPipe(IPipe pipe){
		this.outputPipes.remove(pipe);
	}
}
