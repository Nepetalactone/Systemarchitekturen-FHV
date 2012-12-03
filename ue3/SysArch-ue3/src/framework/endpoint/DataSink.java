package framework.endpoint;

import java.util.Collection;

import framework.pipe.IPipe;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class DataSink<T> implements Serializable{
	
	//public static final boolean ACTIVE_SINK = true;
	//public static final boolean PASSIVE_SINK = false;
	
	protected Collection<IPipe> inputPipes;
	protected boolean active;
	protected Collection<T> finishedObjects;
	
	
        public DataSink(){
            initCollections();
            
        }
	public DataSink(boolean isActive){
		this.active = isActive;
                initCollections();
	}
	
	private void initCollections(){
            this.inputPipes = new ArrayList<IPipe>();
            this.finishedObjects = new ArrayList<T>();
        }
	public void run() throws Exception{
		if(active){
			for(IPipe p: inputPipes){
				T temp = (T) p.pull();
				if(temp != null){
                                    finishedObjects.add(temp);
                                    saveData(temp);
				}
			}
		}
	}
	
	
	public abstract void saveData(T data);
	
	
	public void push(T data) {
		finishedObjects.add(data);
		saveData(data);
	}
	
	public void addInputPipe(IPipe pipe){
		this.inputPipes.add(pipe);
	}
	public void removeInputPipe(IPipe pipe){
		this.inputPipes.remove(pipe);
	}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }
        
}
