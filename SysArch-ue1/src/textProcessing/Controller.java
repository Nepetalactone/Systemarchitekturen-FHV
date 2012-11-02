package textProcessing;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Controller {
	
	public static void main(String[] args){
		try {
		new Controller().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		WordFilter wordFilter = new WordFilter();
		SourcePipe source = new SourcePipe(new LinkedList<String>()); 
		
		
		source.addOutputFilter(wordFilter);
		source.push("asdfasdf asdfafdsaf 34234");
		
		wordFilter.push(source.pull());
		
	}
}
