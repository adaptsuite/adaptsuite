package test.java.org.adaptsuite.test;

import static org.junit.Assert.*;

import main.java.org.adaptsuite.adapter.GenericTest;

import org.junit.Test;

public class TestCase2Sec extends GenericTest {
	

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing two...");
		Thread.sleep(1900);
		System.out.println("Tested two!");
	}
	
	
}
