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
	private IntelliProperties properties;

	public IntelliSuiteBuilder() {
		this(0);
	}
	
	public IntelliSuiteBuilder(Integer runtimeLevel, Class<?>... tests) {
		this.runtimeLevel = runtimeLevel;
		if (tests.length == 0) {
			tests = scanForTests();
		}
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new IntelliSorter());
		this.testQueue = new ArrayDeque<IntelliTestAdapter>(adapters);
		properties = new IntelliProperties();
	}

	private Class<?>[] scanForTests() {
		Class<?>[] tests;
		ClassesFinder classesFinder = new ClasspathFinderFactory().create(false, new String[0], new SuiteType[] { SuiteType.TEST_CLASSES },new Class<?>[] { Object.class },new Class<?>[0],"java.class.path");
		List<Class<?>> scannedClasses = classesFinder.find();
		tests = scannedClasses.toArray(new Class<?>[scannedClasses.size()]);
		return tests;
	}

	public TestSuite suite() {		
		List<Level> levels = properties.getLevelsUpTo(runtimeLevel);		
		String runtimeDescription = (runtimeLevel > 0)? levels.get(runtimeLevel-1).getDescription(): "ALL";		
		TestSuite suite = new TestSuite("IntelliSuite - Level: " + runtimeDescription);
		addTests(levels, suite);
		return suite;
	}
	
	public TestSuite suite(Integer runtime, Integer unit ) {
		String runtimeDescription = " " ;
		switch(unit){
		case 0:
			runtimeDescription = runtime.toString() + "sec";
			break;
		case 1:
			runtimeDescription = runtime.toString() + "min";
			break;
		}
		List<Level> levels = properties.getLevelsUpTo(runtimeDescription);
		TestSuite suite = new TestSuite("IntelliSuite - Level: " + runtimeDescription);
		addTests(levels, suite);
		return suite;
	}

	
	private void addTests(List<Level> levels, TestSuite suite) {
		for (Level level : levels) {
			Long remaining = level.getDuration();
			while (testQueue.peek() != null) {
				IntelliTestAdapter nextTest = testQueue.peek();
				remaining = remaining - nextTest.getTime();
				if (remaining < 0)
					break;
				testQueue.poll();
				suite.addTest(nextTest);
			}
		}
	}
}