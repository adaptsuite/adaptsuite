package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase4 {

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing 1sec...");
		Thread.sleep(900);
		System.out.println("Tested 1sec!");
	}
}
