package org.intellitesting.main;

import org.junit.Test;

public class TestCase1 {

	@Test
	public void testOne() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Testing one...");
	}
}
