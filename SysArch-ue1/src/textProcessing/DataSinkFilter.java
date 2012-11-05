package textProcessing;

import java.io.File;
import java.io.FileWriter;

import filter.Filter;

public class DataSinkFilter<in,out> extends Filter<in, out> {
	private String text;
	
	public void writeFile () {
		try {
			File file = new File("C://Users//filter.txt");
			FileWriter writer = new FileWriter(file, true);
			writer.write(text);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean filter(in data) {
		String page = String.valueOf(data);
		
		if (page.contains("\\end")) {
			text = text + page;
			text = text.replaceAll("\\end", "");
			System.out.println(text);
			writeFile();
			return true;
		} else {
			text = text + page;
		}
		return false;
	}
	
	public DataSinkFilter () {
		text = "";
	}
}
