package org.adaptsuite.test;

import org.junit.Test;

public class TestCase19 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 4.750sec...");
		Thread.sleep(4600);
		System.out.println("Tested 4.750sec!");
	}
}
