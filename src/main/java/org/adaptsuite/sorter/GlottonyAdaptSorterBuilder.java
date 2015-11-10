package main.java.org.adaptsuite.sorter;

import java.util.Comparator;
import java.util.List;

public class GlottonyAdaptSorterBuilder {
	private boolean[] chosenTests;
	
	public boolean[] findTests (List<TestData> testData, int queueSize, Long maxTime) {
		chosenTests = new boolean[queueSize];
		Long totalTime = 0L;
		
		for (TestData obj : testData) {
			
			Double failures = obj.getFailures() > 0 ? obj.getFailures().doubleValue() : 1.0;
			Double coverage = obj.getLineCoverage() > 0 ? obj.getLineCoverage() : 1.0;
			Double lastExecuted = obj.getLastExecuted() > 0 ? obj.getLastExecuted().doubleValue() : 1.0;
			Double frequency = obj.getFrequency() > 0 ? obj.getFrequency() : 1.0;
			Double failFrequency = obj.getFailFrequency() > 0 ? obj.getFailFrequency() : 1.0;
			
			
			Double value = failures * coverage * lastExecuted * frequency * failFrequency;
			
			obj.setTestValue(value);
		}
		
		Comparator<TestData> comparator = new Comparator<TestData> () {
			public int compare(TestData a, TestData b) {
				return (a.getTestValue() < b.getTestValue()) ? 1 : -1;
			}
		};
		testData.sort(comparator);
		
		for (int i=0; i < queueSize-1; i++) {
			if(testData.get(i).getLastExecutionTime() + totalTime < maxTime) {
				chosenTests[i] = true;
				totalTime += testData.get(i).getLastExecutionTime();
			}
		}
		return chosenTests;
	}	

}
