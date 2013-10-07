package org.intellitesting.main;

import org.junit.Test;

public class TestCase2 {

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing two...");
		Thread.sleep(4000);
		System.out.println("Tested two!");		
	}
}
