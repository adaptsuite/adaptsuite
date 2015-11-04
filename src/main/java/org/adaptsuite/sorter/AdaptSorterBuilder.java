package main.java.org.adaptsuite.sorter;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import main.java.org.adaptsuite.adapter.IntelliTestAdapter;

public class AdaptSorterBuilder {
	
	private List<TestData> testData;
	private DegreeOfImportance importance;

	public boolean[] chooseTests (
			Queue<IntelliTestAdapter> testQueue, 
			Long maxTime, 
			Long[] importance, 
			boolean isReverse) {
		
		AdaptSuiteSorter testSorter = new AdaptSuiteSorter();
		int queueSize = testQueue.size();
		this.importance = new DegreeOfImportance(importance);
		
		buildArrays(testQueue, queueSize, isReverse);	
		return testSorter.findTests(testData, queueSize, maxTime);
	}
	
	private void buildArrays (Queue<IntelliTestAdapter> testQueue, int queueSize, boolean isReverse) {
		testData = new ArrayList<TestData>();		
		for (IntelliTestAdapter obj : testQueue) {
			Double frequency =(double)(obj.getTotalExecutions() / obj.getToolExecutions());
			if (isReverse) 
				frequency = 1 / frequency;
			testData.add( new TestData( 
					obj.getTime(),
					obj.getFailure() * this.importance.getFailureIportance(), 
					obj.getCoverage() * this.importance.getLineCoverageImportance(), 
					obj.getLastExecution() * this.importance.getFailureIportance(),
					frequency * this.importance.getFrequencyImportance())
			);
		}
	}

}
