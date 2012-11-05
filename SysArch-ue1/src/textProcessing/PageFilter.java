package textProcessing;

import filter.Filter;

public class PageFilter<in, out> extends Filter<in, out>{
	private final int PAGE_LENGTH;
	private int curLength;
	private String curPage;
	
	public PageFilter(int pageLength){
		PAGE_LENGTH = pageLength;
		curLength = 0;
		curPage = "";
	}
	
	//Push Filter
	public boolean filter(in data) {
		String line = String.valueOf(data);
		StringBuilder sbPage = new StringBuilder();
		
		if (curPage != "") {
			sbPage.append(curPage);
		}
		if (line.contains("\\end")) {
			sbPage.append(line);
			result = (out) sbPage.toString();
			return true;
		}
		if (curLength < PAGE_LENGTH) {
			curLength++;
			sbPage.append(line);
			curPage = sbPage.toString();
		} else {
			sbPage.append("\r\n******\r\n");
			result = (out) sbPage.toString();
			curPage = line;
			curLength = 0;
			return true;
		}
		return false;
	}
	
	//Pull Filter
//	public boolean filter(in data){
//		
//		String[] lineArray = (String[]) data;
//		
//		StringBuilder page = new StringBuilder();
//		LinkedList pages = new LinkedList();
//		int i = 0;
//		
//		while (i < lineArray.length){
//			if (page.length() + lineArray[i].length() <= PAGE_LENGTH){
//				page.append(lineArray[i].toString() + "\n");
//				i++;
//			}else{
//				pages.add(page.toString());
//				pages.add("*************\n");
//				page.delete(0, page.length());
//			}
//		}
//		
//		if (page.length() > 0){
//			pages.add(page.toString());
//		}
//		
//		try {
//			push((in) pages);
//			return true;
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
}
