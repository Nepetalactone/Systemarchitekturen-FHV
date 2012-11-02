package textProcessing;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import filter.Filter;

public class WordFilter<in, out> extends Filter<in,out>{
	
	
	public boolean filter(in data) {
		char[] charArray = (char[])data;
		
		LinkedList<String> words = new LinkedList();
		StringBuilder word = new StringBuilder();
		int i = 0;
		
		while(i < charArray.length){
			if(charArray[i] == ' '){
				words.add(word.toString());
				word.delete(0, word.length());
				i++;
			}else{
				word.append(charArray[i]);
				i++;
			}
		}
		
		if (word.length() > 0){
			words.add(word.toString());
		}
		
		String[] wordArray = (String[]) words.toArray();
		
		try {
			push((in) wordArray);
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
	
	public WordFilter() {
		
	}
	
}
