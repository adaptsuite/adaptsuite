package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase9 {

	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 2.250sec...");
		Thread.sleep(2150);
		System.out.println("Tested 2.250sec!");
	}
}
