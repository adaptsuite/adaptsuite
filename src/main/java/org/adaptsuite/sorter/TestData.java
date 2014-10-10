package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;

	public TestData(Long time, Long failures, Double coverage) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
	}

	public Long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public Long getFailures() {
		return failures;
	}
	
	public Double getLineCoverage(){
		return lineCoverage;
	}

	
	
}
