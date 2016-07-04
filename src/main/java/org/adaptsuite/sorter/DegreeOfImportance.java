package org.adaptsuite.sorter;

public class DegreeOfImportance {
	
	private Long failureImportance;
	private Long lineCoverageImportance;
	private Long lastExecutionImportance;
	private Long frequencyImportance;
	private Long failureFreqImportance;
	
	public DegreeOfImportance (Long [] weights) {
		this.failureImportance =  setDegree(weights[0]);
		this.lineCoverageImportance = setDegree(weights[1]);
		this.lastExecutionImportance = setDegree(weights[2]);
		this.frequencyImportance = setDegree(weights[3]);
		this.failureFreqImportance = setDegree(weights[4]);
	}
	
	public Long setDegree(Long degree) {
		if(degree < 0)
			return 0L;
		if(degree > 5)
			return 5L;
		return degree;
	}
	
	public Long getFailureIportance() {
		return this.failureImportance;
	}
	
	public Long getLineCoverageImportance(){
		return this.lineCoverageImportance;
	}
	
	
	public Long getLastExecutionImportance () {
		return this.lastExecutionImportance;
	}
	
	public Long getFrequencyImportance () {
		return this.frequencyImportance;
	}
	
	public Long getFailFrequencyImportance () {
		return this.failureFreqImportance;
	}

}
