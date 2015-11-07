package main.java.org.adaptsuite.sorter;

import java.util.List;
import java.util.Random;

public class RandomAdaptSorterBuilder {
	private boolean[] chosenTests;
	private Random randomGenerator;
	
	public boolean[] findTests (List<TestData> testData, int queueSize, Long maxTime) {
		Long totalTime = 0L;
		int index;
		
		chosenTests = new boolean[queueSize];
		
		for (int i = 0; i < queueSize; i++) {
			index = randomGenerator.nextInt(testData.size());
			TestData test = testData.remove(index);
			
			if (totalTime + test.getLastExecutionTime() < maxTime) {
				chosenTests[index] = true;
				totalTime += test.getLastExecutionTime();
			}
			
		}
		
		return chosenTests;
	}
}
