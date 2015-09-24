package main.java.org.adaptsuite.coverage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import main.java.org.adaptsuite.adapter.IntelliTestAdapter;
import sun.org.mozilla.javascript.ObjToIntMap;

public class REtrieveCSVData {
	private final String FILE_NAME = "adaptsuite.csv";
	
	public void writer(Queue<IntelliTestAdapter> testQueue) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME), ',');
			for (IntelliTestAdapter obj : testQueue) {
				String [] entry = {
						obj.getName(),
						obj.getTime().toString(),
						obj.getFailure().toString(),
						obj.getLineCoverage().toString(),
						obj.getClassesReached().toString()
				};
				writer.writeNext(entry);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String[]> reader() {
		List<String[]> myEntries = new ArrayList<String[]>();
	    try {
	    	CSVReader reader = new CSVReader(new FileReader(FILE_NAME));
			myEntries = reader.readAll();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myEntries;
	}
}
