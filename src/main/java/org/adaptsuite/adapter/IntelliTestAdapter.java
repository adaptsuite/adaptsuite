package org.adaptsuite.adapter;

import org.adaptsuite.prop.TestProperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

public class IntelliTestAdapter extends JUnit4TestAdapter{

	private TestProperties propertiesManager;

	public IntelliTestAdapter(Class<?> newTestClass) {
		super(newTestClass);
		propertiesManager = TestProperties.newInstance(newTestClass);
	}
	
	public void run(TestResult result) {
		long before = System.currentTimeMillis();
		super.run(result);
		long total = System.currentTimeMillis() - before;
		propertiesManager.set("run.miliseconds",total);
		propertiesManager.set("failure.value", setFailure(result) );
		propertiesManager.close();
	}

	private Long setFailure(TestResult result) {
		Long failureValue = propertiesManager.getLong("failure.value");
		if(failureValue == null)
			failureValue = 0L;
		
		if ((result.errorCount() + result.failureCount()) == 0)
		{
			if (failureValue > 1)
				failureValue -= 1;
		}
		
		failureValue += result.errorCount() + result.failureCount();
		return failureValue;
		
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
}