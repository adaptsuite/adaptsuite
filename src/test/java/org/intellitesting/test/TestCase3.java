package org.intellitesting.test;

import org.junit.Test;

public class TestCase3 {

	@Test
	public void testThree() throws InterruptedException{
		System.out.println("Testing three...");
		Thread.sleep(9000);
		System.out.println("Tested three!");
	}
}
