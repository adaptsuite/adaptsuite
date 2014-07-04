package org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;

	public TestData(Long time, Long failures) {
		this.lastExecutionTime = time;
		this.failures = failures;
	}

	public Long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public Long getFailures() {
		return failures;
	}

	
	
}
