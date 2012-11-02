package textProcessing;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import filter.Filter;

public class LineFilter<in, out> extends Filter<in, out>{

	private final int LINE_LENGTH;
	
	public LineFilter(int lineLength){
		LINE_LENGTH = lineLength;
	}
	
	public boolean filter(in data){
		
		String[] wordArray = (String[]) data;
		
		LinkedList lines = new LinkedList();
		StringBuilder line = new StringBuilder();
		int i = 0;
		
		while (i < wordArray.length){
			if (line.length()+wordArray[i].length()+1 <= LINE_LENGTH){
				line.append(wordArray[i]);
				line.append(' ');
				i++;
			}else{
				lines.add(line.toString());
				line.delete(0, line.length());
			}
		}
		
		if(line.length() > 0){
			lines.add(line.toString());
		}
		
		String[] lineArray = (String[]) lines.toArray();
		
		try {
			push((in) lineArray);
			return true;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
