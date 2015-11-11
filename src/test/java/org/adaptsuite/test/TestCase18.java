package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase18 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 4.5sec...");
		Thread.sleep(4400);
		System.out.println("Tested 4.5sec!");
	}
}
