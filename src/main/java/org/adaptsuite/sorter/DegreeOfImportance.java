package main.java.org.adaptsuite.sorter;

public class DegreeOfImportance {
	
	private Long failureImportance;
	private Long lineCoverageImportance;
	private Long acessedClassImportance;
	
	public DegreeOfImportance (Long failure, Long lineCoverage, Long acessedClass) {
		this.failureImportance = failure;
		this.lineCoverageImportance = lineCoverage;
		this.acessedClassImportance = acessedClass;
	}
	
	public Long getFailureIportance() {
		return failureImportance;
	}
	
	public Long getLineCoverageImportance(){
		return lineCoverageImportance;
	}
	
	public Long getAcessedClassImportance () {
		return acessedClassImportance;
	}

}
