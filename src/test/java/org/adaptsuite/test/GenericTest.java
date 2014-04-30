package org.adaptsuite.test;

abstract class GenericTest {

	protected boolean failedTest = false;
	
	public boolean getfailedTest(){
		return failedTest;
	}
	
	protected void changefailedTest () {
		failedTest = true;
	}
}
