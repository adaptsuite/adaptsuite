package org.adaptsuite.test;

import org.junit.Test;

public class TestCase1Sec {

	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing one...");
		Thread.sleep(990);
		System.out.println("Tested one!");
	}
}
