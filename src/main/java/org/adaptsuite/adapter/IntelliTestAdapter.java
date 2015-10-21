package main.java.org.adaptsuite.adapter;

import main.java.org.adaptsuite.coverage.RetrieveCoverage;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

public class IntelliTestAdapter extends JUnit4TestAdapter{

	private String name;
	private long runtime;
	private Long failures;
	private Double coverage;
	private Long classes;
	private Long lastExecution;

	public IntelliTestAdapter(Class<?> newTestClass, String name, String[] testData) {
		super(newTestClass);
		this.name = name;
		this.runtime = Long.parseLong(testData[1]);
		this.failures = Long.parseLong(testData[2]);
		this.coverage = Double.parseDouble(testData[3]);
		this.classes = Long.parseLong(testData[4]);
		this.lastExecution = Long.parseLong(testData[4]);
	}
	
	public void run(TestResult result) {
		long before = System.currentTimeMillis();
		super.run(result);
		this.runtime = System.currentTimeMillis() - before;
		setFailures(result);
		setCoverage();
		setClassesReached();
		setLastExecution();
	}


	private void setFailures(TestResult result) {
		
		if ((result.errorCount() + result.failureCount()) == 0)	{
			if (this.failures > 1) {
				this.failures /= 2;
				/*Will try to update the coverage/classes reached if we see a 
				 * decrease in the failure count*/
				this.setCoverage();
				this.setClassesReached();
				this.setLastExecution();
			}
		}
		
		this.failures += result.errorCount() + result.failureCount();
		
	}
	
	private Double setCoverage() {
		RetrieveCoverage rc = new RetrieveCoverage();
		this.coverage = rc.getCoverages(this.name)[0];
		return this.coverage;
		
	}
	
	private Long setClassesReached() {
		RetrieveCoverage rc = new RetrieveCoverage();
		this.classes = rc.getCoverages(this.name)[1].longValue();
		return this.classes;
	}
	
	private void setLastExecution() {
		this.lastExecution = 0L;
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
	
	public Long getClassesReached() {
		return this.classes;
	}
	
	public Long getLastExecution() {
		return this.lastExecution;
	}
}