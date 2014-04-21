package org.adaptsuite.test;

import org.junit.Test;

public class TestCase2Sec extends GenericTest {

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing two...");
		Thread.sleep(1900);
		System.out.println("Tested two!");		
	}
}
