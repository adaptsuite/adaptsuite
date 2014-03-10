package org.adaptsuite.suite;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.adaptsuite.adapter.IntelliTestAdapter;
import org.adaptsuite.adapter.IntelliTestAdapters;
import org.adaptsuite.sorter.*;
import org.junit.extensions.cpsuite.ClassesFinder;
import org.junit.extensions.cpsuite.ClasspathFinderFactory;
import org.junit.extensions.cpsuite.SuiteType;

public final class AdaptSuiteBuilder {

	private Queue<IntelliTestAdapter> testQueue;
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
		AdaptSorterBuilder sorter = new AdaptSorterBuilder();
		TestSuite suite = new TestSuite("IntelliSuite - " + runtimeDescription);
		//sorter.chooseTests(this, suite);
		addTests(suite);
		return suite;
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

	private void addTests(TestSuite suite) {
		Long remaining = availableTimeMili;
		while (testQueue.peek() != null) {
			IntelliTestAdapter nextTest = testQueue.peek();
			remaining = remaining - nextTest.getTime();
			if (remaining < 0)
				break;
			testQueue.poll();
			suite.addTest(nextTest);
		}
	}


	public AdaptSuiteBuilder min(int minutes) {
		return sec(minutes * 60);
	}
	
	
	public AdaptSuiteBuilder sec(int seconds) {
		this.availableTimeMili = seconds * 1000;
		return this;
	}
	
	
	public Queue<IntelliTestAdapter> getTestQueue() {
		return this.testQueue;
	}
	
	public long getAvailableTimeMili() {
		return this.availableTimeMili;
	}
}