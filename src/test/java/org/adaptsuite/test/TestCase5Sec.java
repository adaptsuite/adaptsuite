package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase5Sec {

	@Test
	public void testOne() throws InterruptedException {
		System.out.println("Testing 5sec...");
		Thread.sleep(4900);
		System.out.println("Tested 5sec!");
	}
}
