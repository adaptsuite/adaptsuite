package org.intellitesting.test;

import junit.framework.TestSuite;

import org.intellitesting.suite.IntelliSuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class Level1Suite {
	public static TestSuite suite(){
		return new IntelliSuiteBuilder(1).suite();
	}
}
