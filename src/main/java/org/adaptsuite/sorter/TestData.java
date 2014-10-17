package main.java.org.adaptsuite.sorter;

public class TestData {

	private Long lastExecutionTime;
	private Long failures;
	private Double lineCoverage;
	private Long classesReached;

	public TestData(Long time, Long failures, Double coverage, Long classes) {
		this.lastExecutionTime = time;
		this.failures = failures;
		this.lineCoverage = coverage;
		this.classesReached = classes;
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
	
	public Long getClassesReached() {
		return classesReached;
	}

	
	
}
