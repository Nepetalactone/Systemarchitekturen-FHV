package textProcessing;

import java.io.File;
import java.io.FileReader;

import filter.Filter;

public class SourceFilter extends Filter {
	
	public SourceFilter() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void readFile(String fileName) {
		
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			
			int symbol = fileReader.read();
			while (symbol != -1) {
				result = (char) symbol;
				push(result);
				symbol = fileReader.read();
			}
			result = " ";
			push(result);
			result = "\\end";
			push(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}