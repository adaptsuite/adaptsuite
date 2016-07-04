package org.adaptsuite.adapter;

import org.adaptsuite.coverage.RetrieveCoverage;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

public class IntelliTestAdapter extends JUnit4TestAdapter{

	private String name;
	private long runtime;
	private Long failures;
	private Double coverage;
	private Long lastExecution;
	private Long totalExecutions;
	private Long toolExecutions;
	private Long histFailures;

	public IntelliTestAdapter(Class<?> newTestClass, String name, String[] testData) {
		super(newTestClass);
		this.name = name;
		this.runtime = Long.parseLong(testData[1]);
		this.failures = Long.parseLong(testData[2]);
		this.coverage = Double.parseDouble(testData[3]);
		this.lastExecution = Long.parseLong(testData[4]);
		this.totalExecutions = Long.parseLong(testData[5]);
		this.toolExecutions = Long.parseLong(testData[6]);
		this.histFailures = Long.parseLong(testData[7]);
	}
	
	public void run(TestResult result) {
		long before = System.currentTimeMillis();
		super.run(result);
		this.runtime = System.currentTimeMillis() - before;
		setFailures(result);
		setCoverage();
		setLastExecution();
		setTotalExecutions();
	}


	private void setFailures(TestResult result) {
		
		if ((result.errorCount() + result.failureCount()) == 0)	{
			if (this.failures > 1) {
				this.failures /= 2;
			}
		}
		
		this.failures += result.errorCount() + result.failureCount();
		this.histFailures += result.errorCount() + result.failureCount();
		
	}
	
	private Double setCoverage() {
		RetrieveCoverage rc = new RetrieveCoverage();
		this.coverage = rc.getCoverages(this.name)[0];
		return this.coverage;
		
	}
	
	private void setLastExecution() {
		this.lastExecution = 0L;
	}
	
	private void setTotalExecutions() {
		this.totalExecutions += 1L;
	}

	public String getName() {
		return this.name;
	}
	
	public Long getTime() {
		return this.runtime;
	}
	
	public Long getFailure() {
		return this.failures;
	}

	public Double getCoverage() {
		return this.coverage;
	}
	
	public Long getLastExecution() {
		return this.lastExecution;
	}
	
	public Long getTotalExecutions() {
		return this.totalExecutions;
	}
	
	public Long getToolExecutions() {
		return this.toolExecutions;
	}
	
	public Long getHistFailures() {
		return this.histFailures;
	}
}