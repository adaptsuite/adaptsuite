package org.adaptsuite.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase2Sec {

	@Test
	public void testTwo() throws InterruptedException{
		System.out.println("Testing two...");
		Thread.sleep(1900);
		System.out.println("Tested two!");
	}
}