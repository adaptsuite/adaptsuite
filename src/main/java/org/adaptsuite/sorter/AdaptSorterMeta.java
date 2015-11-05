package main.java.org.adaptsuite.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import main.java.org.adaptsuite.adapter.IntelliTestAdapter;

public abstract class AdaptSorterMeta {
	
	List<TestData> testData;
	DegreeOfImportance importance;
	
	void buildArrays (Queue<IntelliTestAdapter> testQueue, int queueSize, boolean isReverse) {
		testData = new ArrayList<TestData>();		
		for (IntelliTestAdapter obj : testQueue) {
			Double frequency =(double)(obj.getTotalExecutions() / obj.getToolExecutions());
			if (isReverse) 
				frequency = 1 / frequency;
			Double failFrequency = (double)(obj.getHistFailures() / obj.getLastExecution());
			testData.add( new TestData( 
					obj.getTime(),
					obj.getFailure() * this.importance.getFailureIportance(), 
					obj.getCoverage() * this.importance.getLineCoverageImportance(), 
					obj.getLastExecution() * this.importance.getFailureIportance(),
					frequency * this.importance.getFrequencyImportance(),
					failFrequency * this.importance.getFailFrequencyImportance())
			);
		}
	}


}
