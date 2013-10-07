package br.com.intellitesting.model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import br.com.intellitesting.main.TestCase1;
import br.com.intellitesting.main.TestCase2;
import br.com.intellitesting.main.TestCase3;
import br.com.intellitesting.prop.IntelliProperties;

@RunWith(AllTests.class)
public final class IntelliTestSuite {
	
	private	static Integer runtimeLevel = 2;
	
	public synchronized static TestSuite suite() {
		IntelliProperties propertiesManager = new IntelliProperties();
		Queue<IntelliTestAdapter> tests = getTests();
		List<Level> levels = propertiesManager.getLevelsUpTo(runtimeLevel);
		TestSuite suite = new TestSuite("AllTests - Level - " + runtimeLevel);
		for (Level level : levels) {
			Long remaining = level.getDuration();
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
	
	public synchronized static void setLevel(Integer level){
		IntelliTestSuite.runtimeLevel = level;
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