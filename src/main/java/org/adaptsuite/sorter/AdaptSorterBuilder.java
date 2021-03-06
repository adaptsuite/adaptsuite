package org.adaptsuite.sorter;

import java.util.List;

public class AdaptSorterBuilder {

	private boolean[] chosenTests;
	private Double[][] knaspackTabble;
	
	public boolean[]  findTests (List<TestData> testData, int queueSize, Long maxTime) {		
		
		chosenTests = new boolean[queueSize];
		knaspackTabble = new Double [queueSize + 1][maxTime.intValue() + 1];
		
		if(shouldRunTrivialCase (testData, queueSize, maxTime))
			return trivialCase();
					
		testsValue(testData, queueSize, maxTime);
		chooseTests(testData, queueSize, maxTime);
		return chosenTests;
	}
	

	private void testsValue (List<TestData> testData, int queueSize, Long maxTime) {
		
		Double a, b;
		
		for (int time = 0; time <= maxTime; time++) {
			knaspackTabble[0][time] = 0.0;	
			for (int i = 1; i <= queueSize; i++) {
				a = knaspackTabble[i-1][time];
				Long lastExecutionTime = testData.get(i-1).getLastExecutionTime();
				
				Long testFailures = testData.get(i-1).getFailures() > 0 ? testData.get(i-1).getFailures() : 1L;
				Double coverage = testData.get(i-1).getLineCoverage() > 0 ? testData.get(i-1).getLineCoverage() : 1.0;
				Long lastExecution = testData.get(i-1).getLastExecuted() > 0 ? testData.get(i-1).getLastExecuted() : 1L;
				Double frequency = testData.get(i-1).getFrequency() > 0 ? testData.get(i-1).getFrequency() : 1.0;
				Double failFrequency = testData.get(i-1).getFailFrequency() > 0 ? testData.get(i-1).getFailFrequency() : 1.0;
				
				if (lastExecutionTime.intValue() > time)
					b = 0.0;
				else
					b = knaspackTabble[i-1][time - lastExecutionTime.intValue()] + 
					( testFailures.doubleValue() * coverage * lastExecution.doubleValue() * frequency);
				
				knaspackTabble[i][time] = Max(a, b);
			}
		}
	}
	
	
	private void chooseTests (List<TestData> testData, int queueSize, Long maxTime) {
		
		Long time = maxTime;
		
		for (int i = queueSize; i >= 1; i--)
			if(knaspackTabble[i][time.intValue()] == knaspackTabble[i-1][time.intValue()])
				chosenTests[i-1] = false;
			else {
				Long executionTime = testData.get(i-1).getLastExecutionTime();
				chosenTests[i-1] = true;
				time -= executionTime;
			}
	}
	
	
	private Double Max (Double a, Double b) {
		
		return a >= b ? a : b;
	}
	
	
	private boolean[] trivialCase() {
		
		for (int i = 0; i < chosenTests.length; i++)
			chosenTests[i] = true;
		
		return chosenTests;
	}
	
	private boolean shouldRunTrivialCase(List<TestData> testData, int queueSize, Long maxTime) {
		
		boolean trivialCase = true;
		Long totalTime = 0L;
		
		for (TestData obj : testData) {
			if(obj.getLastExecutionTime() != 0)
					trivialCase = false;
			totalTime += obj.getLastExecutionTime();
		}
		if (totalTime < maxTime) 
			trivialCase = true;
		
		return trivialCase;
	}
}
