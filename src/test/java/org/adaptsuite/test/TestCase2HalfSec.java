package test.java.org.adaptsuite.test;

import org.junit.Test;

public class TestCase2HalfSec {

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing four...");
		Thread.sleep(2400);
		System.out.println("Tested four!");
	}
}
