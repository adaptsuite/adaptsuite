package org.adaptsuite.test;


import org.junit.Test;

public class TestCase3 {
	
	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing 0.750sec...");
		Thread.sleep(650);
		System.out.println("Tested 0.750sec!");
	}
	
	
}
