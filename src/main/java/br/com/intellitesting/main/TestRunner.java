package br.com.intellitesting.main;

import org.junit.runner.JUnitCore;

import br.com.intellitesting.model.IntelliTestSuite;

public class TestRunner {

	public static void main(String[] args) {
		IntelliTestSuite.setLevel(2);
		JUnitCore.runClasses(IntelliTestSuite.class);
	}

}
