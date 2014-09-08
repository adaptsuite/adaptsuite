package test.java.org.adaptsuite.sorter;

import static org.junit.Assert.*;

import main.java.org.adaptsuite.coverage.RetrieveCoverage;

import org.junit.Test;

public class TestRetriveCoverage {
		
	//@Test
	public void TestGetRightCoverage() {
		RetrieveCoverage rc = new RetrieveCoverage();
		
		assertEquals(0.01, rc.getCoverage("TestCase2Sec"), Double.MIN_VALUE);
		assertEquals(0.01, rc.getCoverage("TestCase3Sec"), Double.MIN_VALUE);
		assertEquals(0.01, rc.getCoverage("TestCase5Sec"), Double.MIN_VALUE);
		

	}
}
