package test.java.org.adaptsuite.sorter;

import static org.junit.Assert.*;

import main.java.org.adaptsuite.coverage.RetrieveCoverage;

import org.junit.Test;

public class TestRetriveCoverage {
		
	//@Test
	public void TestGetRightCoverage() {
		RetrieveCoverage rc = new RetrieveCoverage();
		
		assertEquals(0.02, rc.searchParam("TestCase2Sec"), 10e-2);
		assertEquals(0.02, rc.searchParam("TestCase3Sec"), 10e-2);
		assertEquals(0.02, rc.searchParam("TestCase5Sec"), 10e-2);
		

	}
}
