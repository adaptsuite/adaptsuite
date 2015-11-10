package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase1 {
	
	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing 0.250Sec...");
		Thread.sleep(150);
		System.out.println("Tested 0.250sec!");
	}
	
	
}
