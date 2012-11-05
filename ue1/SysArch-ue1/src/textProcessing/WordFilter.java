package textProcessing;

import filter.Filter;

public class WordFilter<in, out> extends Filter<in,out>{
	private String word;
	
	//Push Filter
	public boolean filter(in data) {
		String symbol = String.valueOf(data);
		StringBuilder sbWord = new StringBuilder();
		
		if (!word.equals("")) {
			sbWord.append(word);
		}
		
		if (symbol.equals(" ") || symbol.contains("\\end")) {
			//Wortende erreicht
			sbWord.append(symbol);
			word = sbWord.toString();
			result = (out) word;
			word = "";
			return true;
		} else {
			sbWord.append(symbol);
			word = sbWord.toString();
		}
		return false;
	}
	
	//Pull Filter	
//	public boolean filter(in data) {
//		//String asdf = String.valueOf(data);
//		//char[] charArray = new char[5];
//		char[] charArray = (char[])data;
//		
//		LinkedList<String> words = new LinkedList();
//		StringBuilder word = new StringBuilder();
//		int i = 0;
//		
//		while(i < charArray.length){
//			if(charArray[i] == ' '){
//				words.add(word.toString());
//				word.delete(0, word.length());
//				i++;
//			}else{
//				word.append(charArray[i]);
//				i++;
//			}
//		}
//		
//		if (word.length() > 0){
//			words.add(word.toString());
//		}
//		
//		String[] wordArray = (String[]) words.toArray();
//		
//		try {
//			push((in) wordArray);
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
//		
//	}
	
	public WordFilter() {
		word = "";
	}
	
}
