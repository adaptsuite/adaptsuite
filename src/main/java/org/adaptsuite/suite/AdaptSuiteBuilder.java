package main.java.org.adaptsuite.suite;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;
import main.java.org.adaptsuite.adapter.IntelliTestAdapter;
import main.java.org.adaptsuite.adapter.IntelliTestAdapters;
import main.java.org.adaptsuite.sorter.AdaptSorterBuilder;
import main.java.org.adaptsuite.sorter.DegreeOfImportance;

import org.junit.extensions.cpsuite.ClassesFinder;
import org.junit.extensions.cpsuite.ClasspathFinderFactory;
import org.junit.extensions.cpsuite.SuiteType;

public final class AdaptSuiteBuilder {

	private Queue<IntelliTestAdapter> testQueue;
	private DegreeOfImportance importance;
	private long availableTimeMili = Long.MAX_VALUE;

	public AdaptSuiteBuilder(Class<?>... tests) {
		if (tests.length == 0) {
			tests = scanForTests();
		}
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new AdaptSorter());
		this.testQueue = new ArrayDeque<IntelliTestAdapter>(adapters);
	}

	private Class<?>[] scanForTests() {
		Class<?>[] tests;
		ClassesFinder classesFinder = new ClasspathFinderFactory().create(
				false, new String[0],
				new SuiteType[] { SuiteType.TEST_CLASSES },
				new Class<?>[] { Object.class }, new Class<?>[0],
				"java.class.path");
		List<Class<?>> scannedClasses = classesFinder.find();
		tests = scannedClasses.toArray(new Class<?>[scannedClasses.size()]);
		return tests;
	}

	public TestSuite build() {
		String runtimeDescription = getSuiteDescription();
		TestSuite suite = new TestSuite("IntelliSuite - " + runtimeDescription);
		addTests(suite);
		return suite;
	}
	
	
	private void addTests (TestSuite suite) {
		importance = new DegreeOfImportance(1L,1L,1L);
		boolean[] chosenTests = new AdaptSorterBuilder().chooseTests(this.testQueue, this.availableTimeMili, 
				this.importance);
		int i = 0;		
		while (testQueue.peek() != null) {
			if(chosenTests[i++])
				suite.addTest(testQueue.peek());			
			testQueue.poll();
		}
	}


	private String getSuiteDescription() {
		if(availableTimeMili == Long.MAX_VALUE)
			return "ALL";
		int seconds = (int)(availableTimeMili/1000);
		int minutes = seconds/60;
		if(seconds < 60)			
			return seconds + " seconds";
		return minutes+ " minutes";
	}
	
	
	public AdaptSuiteBuilder min(int minutes) {
		return sec(minutes * 60);
	}
	
	
	public AdaptSuiteBuilder sec(int seconds) {
		this.availableTimeMili = seconds * 1000;
		return this;
	}
}