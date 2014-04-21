package org.adaptsuite.test;

public class GenericTest {

	protected boolean failedTest = false;
	
	public boolean getfailedTest(){
		return failedTest;
	}
	
	protected void changefailedTest () {
		failedTest = true;
	}
}
