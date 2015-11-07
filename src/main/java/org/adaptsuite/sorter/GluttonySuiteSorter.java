package main.java.org.adaptsuite.sorter;

import java.util.Queue;

import main.java.org.adaptsuite.adapter.IntelliTestAdapter;

public class GluttonySuiteSorter extends AdaptSorterMeta {
	
	public boolean[] chooseTests (
			Queue<IntelliTestAdapter> testQueue, 
			Long maxTime, 
			Long[] importance, 
			boolean isReverse) {
		
		GlottonyAdaptSorterBuilder testSorter = new GlottonyAdaptSorterBuilder();
		int queueSize = testQueue.size();
		this.importance = new DegreeOfImportance(importance);
		
		super.buildArrays(testQueue, queueSize, isReverse);	
		return testSorter.findTests(testData, queueSize, maxTime);
	}

}
