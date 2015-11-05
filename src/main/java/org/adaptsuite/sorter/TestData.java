package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;
	private Long lastExecution;
	private Double frequency;
	private Double failFrequency;
	private Double testValue;

	public TestData(
			Long time, 
			Long failures, 
			Double coverage, 
			Long lastExecution, 
			Double frequency, 
			Double failFrequency) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
		this.lastExecution = lastExecution;
		this.frequency = frequency;
		this.failFrequency = failFrequency;
	}
	
	public void setTestValue(Double testValue) {
		this.testValue = testValue;
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
	
	public Double getFailFrequency() {
		return this.failFrequency;
	}
	
	public Double getTestValue() {
		return this.testValue;
	}
	
}
