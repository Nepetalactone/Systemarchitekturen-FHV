package textProcessing;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Controller {
	private static final String filePath = "C://Users//christoph//Desktop//alice.txt"; 
	
	public static void main(String[] args){
		try {
			new Controller().init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		//Pipe anlegen
		TextPipe sourcePipe = new TextPipe();
		TextPipe wordPipe = new TextPipe();
		TextPipe linePipe = new TextPipe();
		TextPipe pagePipe = new TextPipe();
		
		//Filter anlegen
		SourceFilter sourceFilter = new SourceFilter();
		WordFilter wordFilter = new WordFilter();
		LineFilter lineFilter = new LineFilter(50); //30 entspricht die max. Anzahl der Zeichen einer Zeile
		PageFilter pageFilter = new PageFilter(10); //15 enspricht der zeilenanzahl
		DataSinkFilter dataSinkFilter = new DataSinkFilter();
		
		//Pipe zuweisen
		sourceFilter.addOutputPipe(sourcePipe);
		wordFilter.addOutputPipe(wordPipe);
		lineFilter.addOutputPipe(linePipe);
		pageFilter.addOutputPipe(pagePipe);
		
		//Filter zuweisen
		sourcePipe.addOutputFilter(wordFilter);
		wordPipe.addOutputFilter(lineFilter);
		linePipe.addOutputFilter(pageFilter);
		pagePipe.addOutputFilter(dataSinkFilter);
		
		//Dokument einlesen und Vorgang starten
		sourceFilter.readFile(filePath);
	}
}
