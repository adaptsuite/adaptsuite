package test.java.org.adaptsuite.suite;

import junit.framework.TestSuite;
import java.util.HashMap;
import java.util.Map;

import main.java.org.adaptsuite.suite.AdaptSuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class Seconds5Suite {
	public static TestSuite suite() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		relevance.put(AdaptSuiteBuilder.getErrorConstant(), 2L);
		return new AdaptSuiteBuilder().sec(5).build(relevance);
	}
}