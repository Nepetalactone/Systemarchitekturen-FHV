package filter;

import java.lang.reflect.InvocationTargetException;
import pipe.IPipe;

public abstract class ActiveFilter<in,out> extends Filter<in, out> {
	boolean notEndOfStream;
	
	public ActiveFilter(){
            super();
	}

        
	public abstract void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException;
	
	public void stop() {
		notEndOfStream = false;
	}
        
//        @Override
//        public abstract boolean filter(in data);
}
