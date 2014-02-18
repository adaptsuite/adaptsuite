package org.adaptsuite.test;

import junit.framework.TestSuite;

import org.adaptsuite.suite.AdaptSuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class AllLevelsSuite {
	public static TestSuite suite() {
		return new AdaptSuiteBuilder().build();
	}
}
