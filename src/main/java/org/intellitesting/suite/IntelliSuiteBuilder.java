package org.intellitesting.suite;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.intellitesting.adapter.IntelliTestAdapter;
import org.intellitesting.adapter.IntelliTestAdapters;
import org.intellitesting.adapter.Level;
import org.intellitesting.prop.IntelliProperties;
import org.junit.extensions.cpsuite.ClassesFinder;
import org.junit.extensions.cpsuite.ClasspathFinderFactory;
import org.junit.extensions.cpsuite.SuiteType;

public final class IntelliSuiteBuilder {

	private Queue<IntelliTestAdapter> testQueue;
	private Integer runtimeLevel;

	public IntelliSuiteBuilder() {
		this(Integer.MAX_VALUE);
	}
	
	public IntelliSuiteBuilder(Integer runtimeLevel, Class<?>... tests) {
		this.runtimeLevel = runtimeLevel;
		if (tests.length == 0) {
			tests = scanForTests();
		}
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new IntelliSorter());
		this.testQueue = new ArrayDeque<IntelliTestAdapter>(adapters);
	}

	private Class<?>[] scanForTests() {
		Class<?>[] tests;
		ClassesFinder classesFinder = new ClasspathFinderFactory().create(false, new String[0], new SuiteType[] { SuiteType.TEST_CLASSES },new Class<?>[] { Object.class },new Class<?>[0],"java.class.path");
		List<Class<?>> scannedClasses = classesFinder.find();
		tests = scannedClasses.toArray(new Class<?>[scannedClasses.size()]);
		return tests;
	}

	public TestSuite suite() {
		IntelliProperties propertiesManager = new IntelliProperties();
		List<Level> levels = propertiesManager.getLevelsUpTo(runtimeLevel);		
		String runtimeDescription = (runtimeLevel < Integer.MAX_VALUE)? levels.get(runtimeLevel-1).getDescription(): "ALL";
		
		TestSuite suite = new TestSuite("IntelliSuite - Level: " + runtimeDescription);
		for (Level level : levels) {
			Long remaining = level.getDuration();
			while (true) {
				IntelliTestAdapter nextTest = testQueue.peek();
				if (nextTest == null)
					break;
				remaining = remaining - nextTest.getTime();
				if (remaining < 0)
					break;
				testQueue.poll();
				suite.addTest(nextTest);
			}
		}
		return suite;
	}
}