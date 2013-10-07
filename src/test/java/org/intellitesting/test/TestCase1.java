package org.intellitesting.test;

import org.junit.Test;

public class TestCase1 {

	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing one...");
		Thread.sleep(1000);
		System.out.println("Tested one!");
	}
}
