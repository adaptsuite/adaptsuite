package test.java.org.adaptsuite.suite;

import main.java.org.adaptsuite.suite.AdaptSuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import junit.framework.TestSuite;

@RunWith(AllTests.class)
public class Minutes2Suite {
	public static TestSuite suite() {
		return new AdaptSuiteBuilder().min(2).build();
	}
}
