package textProcessing;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import filter.Filter;

public class PageFilter<in, out> extends Filter<in, out>{

	private final int PAGE_LENGTH;
	
	public PageFilter(int pageLength){
		PAGE_LENGTH = pageLength;
	}
	
	public boolean filter(in data){
		
		String[] lineArray = (String[]) data;
		
		StringBuilder page = new StringBuilder();
		LinkedList pages = new LinkedList();
		int i = 0;
		
		while (i < lineArray.length){
			if (page.length() + lineArray[i].length() <= PAGE_LENGTH){
				page.append(lineArray[i].toString());
				i++;
			}else{
				pages.add(page.toString());
				page.delete(0, page.length());
			}
		}
		
		if (page.length() > 0){
			pages.add(page.toString());
		}
		
		try {
			push((in) pages);
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
