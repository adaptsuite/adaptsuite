package main.java.org.adaptsuite.sorter;

import java.util.List;
import java.util.Random;

public class RandomAdaptSorterBuilder {
	private boolean[] chosenTests;
	private Random randomGenerator;
	
	public boolean[] findTests (List<TestData> testData, int queueSize, Long maxTime) {
		Long totalTime = 0L;
		int index;
		
		while(totalTime <= maxTime) {
			TestData testChosen;
			index = randomGenerator.nextInt(testData.size());
		}
		
		return chosenTests;
	}
}
