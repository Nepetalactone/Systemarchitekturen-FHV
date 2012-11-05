package textProcessing;

import filter.Filter;

public class LineFilter<in, out> extends Filter<in, out>{
	private String curLine;
	private final int LINE_LENGTH;
	
	public LineFilter(int lineLength){
		LINE_LENGTH = lineLength;
		curLine = "";
	}
	
	//Push Filter
	public boolean filter(in data) {
		String word = String.valueOf(data);
		StringBuilder line = new StringBuilder();
		
		if (word.contains("\\end")) {
			line.append(curLine);
			line.append(word);
			curLine = line.toString();
			result = (out) curLine;
			return true;
		}
		
		if((curLine.length()  + word.length()) <= LINE_LENGTH) {
			line.append(curLine);
			line.append(word);
			curLine = line.toString();
			if (curLine.contains("\r\n")) {
				curLine = curLine.replaceAll("\r\n", "");
				curLine = curLine + "\r\n";
				result = (out) curLine;
				curLine = "";
				return true;
			}
			curLine = curLine.replaceAll("\r\n", "");
			return false;
		} else {
			if (!curLine.contains("\r\n")) {
				curLine = curLine.replaceAll("\r\n", "");
				curLine = curLine + "\r\n";
			}
			result = (out) curLine;
			curLine = word;
			return true;
		}
	}

	// Pull Filter
//	public boolean filter(in data){
//		
//		String[] wordArray = (String[]) data;
//		
//		LinkedList lines = new LinkedList();
//		StringBuilder line = new StringBuilder();
//		int i = 0;
//		
//		while (i < wordArray.length){
//			if (line.length()+wordArray[i].length()+1 <= LINE_LENGTH){
//				line.append(wordArray[i]);
//				line.append(' ');
//				i++;
//			}else{
//				lines.add(line.toString());
//				line.delete(0, line.length());
//			}
//		}
//		
//		if(line.length() > 0){
//			lines.add(line.toString());
//		}
//		
//		String[] lineArray = (String[]) lines.toArray();
//		
//		try {
//			push((in) lineArray);
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
