package org.intellitesting.main;

import junit.framework.TestSuite;

import org.intellitesting.model.IntelliSuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class Level3Suite {
	public static TestSuite suite() {
		return new IntelliSuiteBuilder(3).suite();
	}
}
