package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double coverage;

	public TestData(Long time, Long failures, Double coverage) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.coverage = coverage;
	}

	public Long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public Long getFailures() {
		return failures;
	}
	
	public Double getCoverage(){
		return coverage;
	}

	
	
}
