package main.java.org.adaptsuite.sorter;

public class DegreeOfImportance {
	
	private Long failureImportance;
	private Long lineCoverageImportance;
	private Long acessedClassImportance;
	
	public DegreeOfImportance (Long [] weights) {
		this.failureImportance =  setDegree (weights[0]);
		this.lineCoverageImportance = setDegree (weights[1]);
		this.acessedClassImportance = setDegree (weights[2]);
	}
	
	public Long setDegree(Long degree) {
		if(degree < 0)
			return 0L;
		if(degree > 5)
			return 5L;
		return degree;
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
