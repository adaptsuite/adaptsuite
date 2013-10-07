package org.intellitesting.main;

import junit.framework.TestSuite;

import org.intellitesting.model.IntelliBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

@RunWith(AllTests.class)
public class Level2Suite {

	public static TestSuite suite() {
		return new IntelliBuilder(2, TestCase1.class,TestCase2.class,TestCase3.class).suite();
	}
	
}
