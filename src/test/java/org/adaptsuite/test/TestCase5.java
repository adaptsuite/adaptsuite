package org.adaptsuite.test;


import org.junit.Test;

public class TestCase5 {

	@Test
	public void testThree() throws InterruptedException{
		System.out.println("Testing 1.250sec...");
		Thread.sleep(1150);
		System.out.println("Tested 1.250sec!");
	}
}
