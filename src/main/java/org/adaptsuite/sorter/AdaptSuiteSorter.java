package org.adaptsuite.sorter;

import java.lang.Math;

public class AdaptSuiteSorter {

	private boolean[] chosenTests;
	private Long[][] knaspackTabble;
	
	private void  ChooseTests (Long[] testTime, Long[] testFailure, int queueSize, Long maxTime) {
		
		Long a, b;
		chosenTests = new boolean[queueSize];
		knaspackTabble = new Long [queueSize][maxTime.intValue()];
		
		for (int time = 0; time < maxTime; time++) {
			knaspackTabble[0][time] = (Long)0L;
			
			for (int i = 1; i < queueSize; i++) {
				a = knaspackTabble[i-1][time];
				if (testTime[i].intValue() > time)
					b = (Long)0L;
				else
					b = knaspackTabble[i-1][time - testTime[i].intValue()] + testFailure[i];
				
				knaspackTabble[i][time] = Max(a, b);
			}
		}
	}
	
	
	private Long Max (Long a, Long b) {
		
		return a.longValue() > b.longValue() ? a : b;
	}
}
