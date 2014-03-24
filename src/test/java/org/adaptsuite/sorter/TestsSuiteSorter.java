package org.adaptsuite.sorter;

import org.junit.Test;


public class TestsSuiteSorter {
	@Test
	public void TestSorter() {
		
		Long[] time = {5000L,3000L,2000L};
		Long[] failures = {1L,1L,1L};
		AdaptSuiteSorter test = new AdaptSuiteSorter();
		boolean[] result = test.FindTests(time, failures, 3, 5000L);
		
		for(int i=0; i<3; i++)
			System.out.print(result[i] + " ");
		System.out.println();
	}
}
