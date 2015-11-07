package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase2Sec {
	
	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing 2sec...");
		Thread.sleep(1900);
		System.out.println("Tested 2sec!");
	}
	
	
}
