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
		propertiesManager.set("lines.coverage", getLineCoverage());
		propertiesManager.set("classes.reached", getClassesReached());
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
	
	private Double[] setCoverage()
	{
		RetrieveCoverage rc = new RetrieveCoverage();
		return rc.getCoverages(this.name);	
		
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
			failure = 1L;
		return failure;
	}

	public Double getLineCoverage() {
		Double coverage = propertiesManager.getDouble("lines.coverage");
		if (coverage == null)
			coverage = setCoverage()[0];	
		return coverage;
	}
	
	public Long getClassesReached() {
		Long classes = propertiesManager.getLong("classes.reached");
		if (classes == null)
			classes = setCoverage()[1].longValue();	
		return classes;
	}
}