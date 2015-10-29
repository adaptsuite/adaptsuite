package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;
	private Long lastExecution;
	private Double frequency;

	public TestData(Long time, Long failures, Double coverage, Long lastExecution, Double frequency) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
		this.lastExecution = lastExecution;
		this.frequency = frequency;
	}

	public Long getLastExecutionTime() {
		return this.lastExecutionTime;
	}

	public Long getFailures() {
		return this.failures;
	}
	
	public Double getLineCoverage(){
		return this.lineCoverage;
	}

	public Long getLastExecution() {
		return this.lastExecution;
	}
	
	public Double getFrequency() {
		return this.frequency;
	}
	
}
