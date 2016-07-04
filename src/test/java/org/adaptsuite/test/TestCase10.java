package org.adaptsuite.test;

import org.junit.Test;

public class TestCase10 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 2.5sec...");
		Thread.sleep(2400);
		System.out.println("Tested 2.5sec!");
	}
}
