package org.intellitesting.model;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import junit.framework.TestSuite;

import org.intellitesting.prop.IntelliProperties;

public final class IntelliBuilder {

	private Queue<IntelliTestAdapter> testQueue;
	private Integer runtimeLevel;

	public IntelliBuilder(Integer runtimeLevel, Class<?>... tests) {
		this.runtimeLevel = runtimeLevel;
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new IntelliSorter());
		this.testQueue = new ArrayDeque<IntelliTestAdapter>(adapters);
	}

	public TestSuite suite() {
		IntelliProperties propertiesManager = new IntelliProperties();
		List<Level> levels = propertiesManager.getLevelsUpTo(runtimeLevel);
		TestSuite suite = new TestSuite("AllTests - Level - " + runtimeLevel);
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