package test.java.org.adaptsuite.sorter;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.java.org.adaptsuite.sorter.AdaptSuiteSorter;
import main.java.org.adaptsuite.sorter.TestData;


public class TestsSuiteSorter {
	
	//@Test
	public void TestSorter() {
		
		List<TestData> testData = Arrays.asList(
				new TestData(5000L, 1L, 1.0, 1L, 1.0),
				new TestData(3000L, 1L, 1.0, 1L, 1.0),
				new TestData(2000L, 1L, 1.0, 1L, 1.0)
		);
		AdaptSuiteSorter test = new AdaptSuiteSorter();
		
		boolean[] expected = {false, true, true};
		boolean[] received = test.findTests(testData, 3, 5000L);
		
		
		for(int i = 0; i < expected.length; i++)
			assertEquals(expected[i], received[i]);
	}
}
