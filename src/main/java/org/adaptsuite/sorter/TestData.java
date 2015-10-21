package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;
	private Long classesReached;
	private Long lastExecution;

	public TestData(Long time, Long failures, Double coverage, Long classes, Long lastExecution) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
		this.classesReached = classes;
		this.lastExecution = lastExecution;
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
	
	public Long getClassesReached() {
		return this.classesReached;
	}

	public Long getLastExecution() {
		return this.lastExecution;
	}
	
}
