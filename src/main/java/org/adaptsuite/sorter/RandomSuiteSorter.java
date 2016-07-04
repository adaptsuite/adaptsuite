package org.adaptsuite.sorter;

import java.util.Queue;

import org.adaptsuite.adapter.IntelliTestAdapter;

public class RandomSuiteSorter extends AdaptSorterMeta {
	
	public boolean[] chooseTests (
			Queue<IntelliTestAdapter> testQueue, 
			Long maxTime, 
			Long[] importance, 
			boolean isReverse) {
		
		RandomAdaptSorterBuilder testSorter = new RandomAdaptSorterBuilder();
		int queueSize = testQueue.size();
		this.importance = new DegreeOfImportance(importance);
		
		super.buildArrays(testQueue, queueSize, isReverse);	
		return testSorter.findTests(testData, queueSize, maxTime);
	}

}
