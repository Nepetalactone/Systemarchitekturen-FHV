package framework.filter;

import java.lang.reflect.InvocationTargetException;

import framework.pipe.IPipe;

public abstract class ActiveFilter<in,out> extends Filter<in, out> {
	boolean notEndOfStream;
	
	public ActiveFilter(){
            super();
            notEndOfStream = true;
	}
        
	public boolean isNotEndOfStream() {
		return notEndOfStream;
	}

	public void setNotEndOfStream(boolean notEndOfStream) {
		this.notEndOfStream = notEndOfStream;
	}

	public void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		in data = null;
		while(notEndOfStream){
			for(IPipe inPipe: inputPipes){
				data = (in) inPipe.pull();
				if(data != null){
					if(filter(data)){
						for(IPipe outPipe: outputPipes){
							outPipe.push(result);
						}
					}
				}
			}
			if(data == null){
				stop();
			}
		}
		
		
	}
	
	public void stop() {
		notEndOfStream = false;
	}
}
