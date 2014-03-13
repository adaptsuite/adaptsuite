package org.adaptsuite.sorter;

public class AdaptSuiteSorter {

	private boolean[] chosenTests;
	private Long[][] knaspackTabble;
	
	public boolean[]  FindTests (Long[] testTime, Long[] testFailure, int queueSize, Long maxTime) {
		
		
		chosenTests = new boolean[queueSize];
		knaspackTabble = new Long [queueSize + 1][maxTime.intValue() + 1];
		TestsValue(testTime, testFailure, queueSize, maxTime);
		ChooseTests(testTime, queueSize, maxTime);
		return chosenTests;
		
	}
	
	private void  TestsValue (Long[] testTime, Long[] testFailure, int queueSize, Long maxTime) {
		
		Long a, b;
		
		for (int time = 0; time <= maxTime; time++) {
			knaspackTabble[0][time] = (Long)0L;
			
			for (int i = 1; i <= queueSize; i++) {
				a = knaspackTabble[i-1][time];
				if (testTime[i-1].intValue() > time)
					b = (Long)0L;
				else
					b = knaspackTabble[i-1][time - testTime[i-1].intValue()] + testFailure[i-1];
				
				knaspackTabble[i][time] = Max(a, b);
			}
		}
	}
	
	
	private void ChooseTests (Long[] testTime, int queueSize, Long maxTime) {
		
		Long time = maxTime;
		
		for (int i = queueSize; i >= 1; i--)
			if(knaspackTabble[i][time.intValue()] == knaspackTabble[i-1][time.intValue()])
				chosenTests[i-1] = false;
			else {
				chosenTests[i-1] = true;
				time -= testTime[i-1];
			}
	}
	
	
	private Long Max (Long a, Long b) {
		
		return a.longValue() >= b.longValue() ? a : b;
	}
	
}
