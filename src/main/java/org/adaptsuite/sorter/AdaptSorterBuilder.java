package org.adaptsuite.sorter;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.adaptsuite.adapter.IntelliTestAdapter;

/** Ideia dessa classe é escolher o maior número de testes possíveis dentro do 
 *  tempo limite, e não mais pegar os primeiros testes até estourar o tempo.*/
public class AdaptSorterBuilder {
	
	private List<TestData> testData;

	public boolean[] chooseTests (Queue<IntelliTestAdapter> testQueue, Long maxTime) {
		
		AdaptSuiteSorter testSorter = new AdaptSuiteSorter();
		int queueSize = testQueue.size();
		
		buildArrays(testQueue, queueSize);
		return testSorter.findTests(testData, queueSize, maxTime);
	}
	
	private void buildArrays (Queue<IntelliTestAdapter> testQueue, int queueSize) {
		testData = new ArrayList<TestData>();		
		for (IntelliTestAdapter obj : testQueue) {
			testData.add(new TestData(obj.getTime(),obj.getFailure()));
		}
	}

}
