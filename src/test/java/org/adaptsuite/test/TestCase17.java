package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase17 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 4.250sec...");
		Thread.sleep(4150);
		System.out.println("Tested 4.250sec!");
	}
}
