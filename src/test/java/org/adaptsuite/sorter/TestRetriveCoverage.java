package org.adaptsuite.sorter;

import static org.junit.Assert.*;

import org.adaptsuite.coverage.RetrieveCoverage;

import org.junit.Test;

public class TestRetriveCoverage {
		
	//@Test
	public void TestGetRightCoverage() {
		RetrieveCoverage rc = new RetrieveCoverage();
		
		assertEquals(0.02, rc.getCoverages("TestCase2Sec")[0], 10e-2);
		assertEquals(0.02, rc.getCoverages("TestCase3Sec")[0], 10e-2);
		assertEquals(0.02, rc.getCoverages("TestCase5Sec")[0], 10e-2);
		

	}
}
