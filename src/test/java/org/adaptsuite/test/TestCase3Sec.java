package test.java.org.adaptsuite.test;

import main.java.org.adaptsuite.adapter.GenericTest;

import org.junit.Test;

public class TestCase3Sec extends GenericTest {

	@Test
	public void testThree() throws InterruptedException{
		System.out.println("Testing three...");
		Thread.sleep(2900);
		System.out.println("Tested three!");
	}
}
