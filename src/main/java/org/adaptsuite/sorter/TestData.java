package org.adaptsuite.sorter;

public class TestData implements Comparable<TestData>{

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;
	private Long lastExecuted;
	private Double frequency;
	private Double failFrequency;
	private Double testValue;
	private String name;

	public TestData(
			Long time, 
			Long failures, 
			Double coverage, 
			Long lastExecution, 
			Double frequency, 
			Double failFrequency,
			String name) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
		this.lastExecuted = lastExecution;
		this.frequency = frequency;
		this.failFrequency = failFrequency;
		this.name = name;
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

	public Long getLastExecuted() {
		return this.lastExecuted;
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
	
	public String getName() {
		return this.name;
	}

	public int compareTo(TestData o) {
		return (this.getTestValue() < o.getTestValue()) ? 1 : -1;
	}
	
}
