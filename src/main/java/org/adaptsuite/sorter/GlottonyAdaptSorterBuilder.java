package org.adaptsuite.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GlottonyAdaptSorterBuilder {
	private List<String> chosenTests;
	
	public List<String> findTests (List<TestData> testData, int queueSize, Long maxTime) {
		chosenTests = new ArrayList<String>();
		Long totalTime = 0L;
		
		for (TestData obj : testData) {
			
			Double failures = obj.getFailures() > 0 ? obj.getFailures().doubleValue() : 1.0;
			Double coverage = obj.getLineCoverage() > 0 ? obj.getLineCoverage() : 1.0;
			Double lastExecuted = obj.getLastExecuted() > 0 ? obj.getLastExecuted().doubleValue() : 1.0;
			Double frequency = obj.getFrequency() > 0 ? obj.getFrequency() : 1.0;
			
			
			Double value = failures * coverage * lastExecuted * frequency;
			
			obj.setTestValue(value);
		}
		
		Collections.sort(testData);
		
		for (int i=0; i < queueSize-1; i++) {
			if(testData.get(i).getLastExecutionTime() + totalTime < maxTime) {
				chosenTests.add(testData.get(i).getName());
				totalTime += testData.get(i).getLastExecutionTime();
			}
		}
		return chosenTests;
	}	

}
