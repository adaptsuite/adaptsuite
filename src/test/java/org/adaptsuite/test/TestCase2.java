package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase2 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 0.5sec...");
		Thread.sleep(400);
		System.out.println("Tested 0.5sec!");
	}
}
