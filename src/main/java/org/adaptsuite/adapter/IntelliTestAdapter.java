package main.java.org.adaptsuite.adapter;

import main.java.org.adaptsuite.coverage.RetrieveCoverage;
import main.java.org.adaptsuite.prop.TestProperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

public class IntelliTestAdapter extends JUnit4TestAdapter{

	private TestProperties propertiesManager;
	private String name;

	public IntelliTestAdapter(Class<?> newTestClass, String name) {
		super(newTestClass);
		propertiesManager = TestProperties.newInstance(newTestClass);
		this.name = name;
	}
	
	public void run(TestResult result) {
		long before = System.currentTimeMillis();
		super.run(result);
		long total = System.currentTimeMillis() - before;
		propertiesManager.set("run.miliseconds",total);
		propertiesManager.set("failure.value", setFailure(result) );
		propertiesManager.set("coverage.value", getCoverage());
		propertiesManager.close();
	}

	private Long setFailure(TestResult result) {
		Long failureValue = propertiesManager.getLong("failure.value");
		if(failureValue == null)
			failureValue = 1L;
		
		if ((result.errorCount() + result.failureCount()) == 0)
		{
			if (failureValue > 1)
				{
					failureValue /= 2;
					propertiesManager.set("coverage.value", setCoverage());
				}
		}
		
		failureValue += result.errorCount() + result.failureCount();
		return failureValue;
		
	}
	
	private double setCoverage()
	{
		RetrieveCoverage rc = new RetrieveCoverage();
		return rc.getCoverage(this.name);	
		
	}

	public Long getTime() {
		Long mili = propertiesManager.getLong("run.miliseconds");
		if(mili == null)
			mili = 0L;
		return mili;
	}
	
	public Long getFailure() {
		Long failure = propertiesManager.getLong("failure.value");
		if(failure == null)
			failure = 0L;
		return failure;
	}

	public Double getCoverage() {
		Double coverage = propertiesManager.getDouble("coverage.value");
		if (coverage == null)
			coverage = setCoverage();	
		return coverage;
	}
}