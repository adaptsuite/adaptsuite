package main.java.org.adaptsuite.sorter;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import main.java.org.adaptsuite.adapter.IntelliTestAdapter;

public class AdaptSorterBuilder {
	
	private List<TestData> testData;
	private DegreeOfImportance importance;

	public boolean[] chooseTests (Queue<IntelliTestAdapter> testQueue, Long maxTime, DegreeOfImportance importance) {
		
		AdaptSuiteSorter testSorter = new AdaptSuiteSorter();
		int queueSize = testQueue.size();
		this.importance = importance;
		
		buildArrays(testQueue, queueSize);
		return testSorter.findTests(testData, queueSize, maxTime);
	}
	
	private void buildArrays (Queue<IntelliTestAdapter> testQueue, int queueSize) {
		testData = new ArrayList<TestData>();		
		for (IntelliTestAdapter obj : testQueue) {
			testData.add( new TestData( obj.getTime(),obj.getFailure(), obj.getLineCoverage(), obj.getClassesReached() ) );
		}
	}

}
