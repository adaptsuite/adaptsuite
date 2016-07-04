package org.adaptsuite.test;

import org.junit.Test;

public class TestCase15 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 3.750sec...");
		Thread.sleep(3650);
		System.out.println("Tested 3.750sec!");
	}
}
