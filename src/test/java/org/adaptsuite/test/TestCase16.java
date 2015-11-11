package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase16 {
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 4sec...");
		Thread.sleep(3900);
		System.out.println("Tested 4sec!");
	}
}
