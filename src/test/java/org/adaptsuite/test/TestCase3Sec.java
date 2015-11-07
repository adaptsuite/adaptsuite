package test.java.org.adaptsuite.test;


import org.junit.Test;

public class TestCase3Sec {

	@Test
	public void testThree() throws InterruptedException{
		System.out.println("Testing 3sec...");
		Thread.sleep(2900);
		System.out.println("Tested 3sec!");
	}
}
