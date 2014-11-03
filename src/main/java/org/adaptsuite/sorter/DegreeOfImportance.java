package main.java.org.adaptsuite.sorter;

public class DegreeOfImportance {
	
	private Long failureImportance;
	private Long lineCoverageImportance;
	private Long acessedClassImportance;
	
	public DegreeOfImportance (Long [] weights) {
		this.failureImportance = (long) weights[0];
		this.lineCoverageImportance = (long) weights[1];
		this.acessedClassImportance = (long) weights[2];
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
