package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase4Sec {
	
	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing 4sec...");
		Thread.sleep(3900);
		System.out.println("Tested 4sec!");
	}
	
	
}
