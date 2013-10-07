package br.com.intellitesting.model;

import br.com.intellitesting.prop.TestProperties;
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
		propertiesManager.close();
	}

	public Long getTime() {
		Long mili = propertiesManager.getLong("run.miliseconds");
		if(mili == null)
			mili = 0L;
		return mili;
	}
}
