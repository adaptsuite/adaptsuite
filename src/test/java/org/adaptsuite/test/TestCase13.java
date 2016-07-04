package org.adaptsuite.test;

import org.junit.Test;

public class TestCase13 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 3.250sec...");
		Thread.sleep(3150);
		System.out.println("Tested 3.250sec!");
	}
}
