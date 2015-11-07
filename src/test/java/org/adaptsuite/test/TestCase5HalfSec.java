package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase5HalfSec {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 5.5sec...");
		Thread.sleep(5400);
		System.out.println("Tested 5.5sec!");
	}
}
