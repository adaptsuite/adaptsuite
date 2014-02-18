package org.adaptsuite.suite;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.adaptsuite.adapter.IntelliTestAdapter;
import org.adaptsuite.adapter.IntelliTestAdapters;
import org.junit.extensions.cpsuite.ClassesFinder;
import org.junit.extensions.cpsuite.ClasspathFinderFactory;
import org.junit.extensions.cpsuite.SuiteType;

public final class IntelliSuiteBuilder {

	private Queue<IntelliTestAdapter> testQueue;
	private long availableTimeMili = Long.MAX_VALUE;

	public IntelliSuiteBuilder(Class<?>... tests) {
		if (tests.length == 0) {
			tests = scanForTests();
		}
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new IntelliSorter());
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

	private String getSuiteDescription() {
		if(availableTimeMili == Long.MAX_VALUE)
			return "ALL";
		return (availableTimeMili/1000) + " seconds";
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

	public IntelliSuiteBuilder sec(int seconds) {
		this.availableTimeMili = seconds * 1000;
		return this;
	}
}