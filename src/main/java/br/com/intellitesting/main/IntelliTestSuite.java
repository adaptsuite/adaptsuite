package br.com.intellitesting.main;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public final class IntelliTestSuite {
	
	private static Integer level = 3;
	
	public static TestSuite suite() {
		PropertiesManager propertiesManager = PropertiesManager.newInstance();
		Queue<IntelliTestAdapter> tests = getTests();		
		String[] levels = propertiesManager.get("intellitesting.levels").split(",");
		String[] timings = propertiesManager.get("intellitesting.timings").split(",");
		Long[] expected = new Long[timings.length];
		for(int i = 0; i < timings.length; i++){
			expected[i] = Long.valueOf(timings[i]); 
		}
		if(level == null)
			level = levels.length;
		TestSuite suite = new TestSuite("Level - " + level);
		for (int i = 0; i <= level - 1 ;i++) {
			Long remaining = expected[i] * 1000;
			while(true){ 
				IntelliTestAdapter nextTest = tests.peek();
				if(nextTest == null)
					break;
				remaining = remaining - nextTest.getTime();
				if(remaining < 0)
					break;
				tests.poll();
				suite.addTest(nextTest);
			}
		}		
		return suite;
	}
	
	private static Queue<IntelliTestAdapter> getTests() {
		List<IntelliTestAdapter> list = Arrays.asList(new IntelliTestAdapter(TestCase1.class),
						new IntelliTestAdapter(TestCase2.class),
						new IntelliTestAdapter(TestCase3.class));
		Collections.sort(list,new Comparator<IntelliTestAdapter>() {
			public int compare(IntelliTestAdapter t1, IntelliTestAdapter t2) {
				return t1.getTime().compareTo(t2.getTime());
			}			
		});
		ArrayDeque<IntelliTestAdapter> deque = new ArrayDeque<IntelliTestAdapter>(list);		
		return deque;
	}
}