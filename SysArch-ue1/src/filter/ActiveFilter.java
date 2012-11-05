package filter;

import java.lang.reflect.InvocationTargetException;
import pipe.IPipe;

public abstract class ActiveFilter<in,out> extends Filter<in, out> {
	boolean notEndOfStream;
	
	public ActiveFilter(){
		super();
	}

	public void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException{
		notEndOfStream = true;
		while (notEndOfStream) {
			for (IPipe inputPipe : inputPipes) {
				pull();
				for (IPipe outputPipe : outputPipes){
					//outputPipe.push(getDeepCopy());
					outputPipe.push(result);
				}
			}
		}
	}
	
	public void stop() {
		notEndOfStream = false;
	}
}
