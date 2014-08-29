package test.java.org.adaptsuite.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.org.adaptsuite.coverage.CoverageFile;

public class TestCoverageFile {
	
	@Test
	public void TestGetRightCoverage()
	{
		CoverageFile cf = new CoverageFile();
		
		assertEquals(1.0, cf.getCoverage("TestCase2Sec"), Double.MIN_VALUE);
	}
	
	@Test
	public void TestDoesntExist()
	{
		CoverageFile cf = new CoverageFile();
		
		assertEquals(1.0, cf.getCoverage("TestCase2Sc"), Double.MIN_VALUE);
	}
}
