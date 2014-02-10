package org.intellitesting.test;

import org.intellitesting.suite.IntellSuiteEnum;
import org.intellitesting.suite.IntelliSuiteBuilder;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class LevelAnySuite {
	public static TestSuite suite() {
		return new IntelliSuiteBuilder().suite(15, 0);
	}
	
}
