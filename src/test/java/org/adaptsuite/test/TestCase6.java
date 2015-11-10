package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase6 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 1.5sec...");
		Thread.sleep(1400);
		System.out.println("Tested 1.5sec!");
	}
}
