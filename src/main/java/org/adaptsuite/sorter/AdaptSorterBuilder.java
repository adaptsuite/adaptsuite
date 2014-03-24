package org.adaptsuite.sorter;


import java.util.Queue;

import org.adaptsuite.adapter.IntelliTestAdapter;
import org.adaptsuite.suite.AdaptSuiteBuilder;

import junit.framework.TestSuite;

/*Ideia dessa classe é escolher o maior número de testes possíveis dentro do 
 * tempo limite, e não mais pegar os primeiros testes até estourar o tempo.*/
public class AdaptSorterBuilder {
	
	private Long[] testTime;
	private Long[] testFailure;
	
	public boolean[] chooseTests (Queue<IntelliTestAdapter> testQueue, Long maxTime) {
		
		AdaptSuiteSorter testSorter = new AdaptSuiteSorter();
		int queueSize = testQueue.size();
		
		buildArrays(testQueue, queueSize);
		return testSorter.FindTests(testTime, testFailure, queueSize, maxTime);
	}
	
	private void buildArrays (Queue<IntelliTestAdapter> testQueue, int queueSize) {
		
		testTime = new Long[queueSize];
		testFailure = new Long[queueSize];
		int i = 0;
		
		for (IntelliTestAdapter obj : testQueue) {
			testTime[i] = obj.getTime();
			testFailure[i++] = obj.getFailure();
		}
	}

}
