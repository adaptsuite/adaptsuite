package main.java.org.adaptsuite.sorter;

import java.util.List;

public class GlottonyAdaptSorterBuilder {
	private boolean[] chosenTests;
	
	public boolean[] findTests (List<TestData> testData, int queueSize, Long maxTime) {
		chosenTests = new boolean[queueSize];
		
		return chosenTests;
	}

}
