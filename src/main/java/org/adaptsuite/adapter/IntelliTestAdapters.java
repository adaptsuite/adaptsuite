package main.java.org.adaptsuite.adapter;

import java.util.ArrayList;
import java.util.List;

import main.java.org.adaptsuite.coverage.RetrieveCSVData;

@SuppressWarnings("serial")
public class IntelliTestAdapters extends ArrayList<IntelliTestAdapter>{
	
	private String[] mockData = {"Test", "0", "1", "1.0", "1", "1", "1"}; 
	
	public IntelliTestAdapters(Class<?>[] tests) {
		RetrieveCSVData csv = new RetrieveCSVData();
		List<String[]> allData = csv.reader();
		
		for (Class<?> test : tests) {
			String testName = getName(test);
			String[] testData = getData(allData, testName);
			if(testData == null)
				testData = this.mockData;
			
			add(new IntelliTestAdapter(test, testName, testData));
		}
	}
	
	private String[] getData(List<String[]> allData, String testName) {
		if (allData.isEmpty())
			return null;
		for (String[] obj : allData) {
			if (obj[0].equals(testName))
				return obj;
		}
		return null;
	}
	
	private String getName (Class <?> test) {
		String path = test.getName();
		String name[] = path.split("\\.");
		
		return name[name.length - 1];
		
	}

}
