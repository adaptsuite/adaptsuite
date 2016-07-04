package org.adaptsuite.coverage;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


import org.adaptsuite.adapter.IntelliTestAdapter;

public class RetrieveCSVData {
	private final String FILE_NAME = "adaptsuite.csv";
	
	public void writer(Queue<IntelliTestAdapter> testQueue) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME), ',');
			for (IntelliTestAdapter obj : testQueue) {
				Long toolExecutions = obj.getToolExecutions() + 1L;
				Long lastExecution = obj.getLastExecution() + 1L;
				String [] entry = {
						obj.getName(),
						obj.getTime().toString(),
						obj.getFailure().toString(),
						obj.getCoverage().toString(),
						lastExecution.toString(),
						obj.getTotalExecutions().toString(),
						toolExecutions.toString(),
						obj.getHistFailures().toString()
				};
				writer.writeNext(entry);
			}
			writer.close();
		} catch (IOException e) {
			return;
		}
	}
	
	/*The returned fields of the csv are as follow:
	 * 0: name
	 * 1: last runtime
	 * 2: number of failures/errors
	 * 3: coverage as it is in the Eclemma report
	 * 4: last time the test ran 
	 * 5: number of times the test was chosen
	 * 6: number of times the tool ran
	 * 7: number of failures this test have, historically  */ 
	public List<String[]> reader() {
		List<String[]> myEntries = new ArrayList<String[]>();
	    try {
	    	CSVReader reader = new CSVReader(new FileReader(FILE_NAME));
			myEntries = reader.readAll();
			reader.close();
		} catch (IOException e) {
			
		}
		return myEntries;
	}
}
