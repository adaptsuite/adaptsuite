package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase8 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 2sec...");
		Thread.sleep(1900);
		System.out.println("Tested 2sec!");
	}
}
