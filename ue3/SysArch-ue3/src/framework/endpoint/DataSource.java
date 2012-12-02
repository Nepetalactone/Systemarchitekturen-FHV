package framework.endpoint;

import java.util.Collection;

import framework.pipe.IPipe;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class DataSource<T> implements Serializable{
	
    //public static final boolean ACTIVE_SOURCE = true;
   // public static final boolean PASSIVE_SOURCE = false;

    protected Collection<IPipe> outputPipes;
    protected boolean active;
    protected T data;


    public DataSource(){
        initCollections();
        active = false;
    }

    public DataSource(boolean isActive){
            this.active = isActive;
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
            if(active){
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

    public Collection<IPipe> getOutputPipes() {
        return outputPipes;
    }

    public void setOutputPipes(Collection<IPipe> outputPipes) {
        this.outputPipes = outputPipes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
