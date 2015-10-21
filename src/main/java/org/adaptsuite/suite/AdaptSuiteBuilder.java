package main.java.org.adaptsuite.suite;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Map;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import main.java.org.adaptsuite.adapter.IntelliTestAdapter;
import main.java.org.adaptsuite.adapter.IntelliTestAdapters;
import main.java.org.adaptsuite.sorter.AdaptSorterBuilder;
import main.java.org.adaptsuite.sorter.RelevanceConstants;
import main.java.org.adaptsuite.coverage.RetrieveCSVData;

import org.junit.extensions.cpsuite.ClassesFinder;
import org.junit.extensions.cpsuite.ClasspathFinderFactory;
import org.junit.extensions.cpsuite.SuiteType;

public final class AdaptSuiteBuilder {

	private Queue<IntelliTestAdapter> testQueue;
	private Queue<IntelliTestAdapter> testsToRun;
	private Long[] importance;
	private long availableTimeMili = Long.MAX_VALUE;
	

	public AdaptSuiteBuilder(Class<?>... tests) {
		if (tests.length == 0) {
			tests = scanForTests();
		}
		IntelliTestAdapters adapters = new IntelliTestAdapters(tests);
		Collections.sort(adapters, new AdaptSorter());
		this.testQueue = new ArrayDeque<IntelliTestAdapter>(adapters);
		this.testsToRun = new ArrayDeque<IntelliTestAdapter>();
	}

	private Class<?>[] scanForTests() {
		Class<?>[] tests;
		ClassesFinder classesFinder = new ClasspathFinderFactory().create(
				false, new String[0],
				new SuiteType[] { SuiteType.TEST_CLASSES },
				new Class<?>[] { Object.class }, new Class<?>[0],
				"java.class.path");
		List<Class<?>> scannedClasses = classesFinder.find();
		tests = scannedClasses.toArray(new Class<?>[scannedClasses.size()]);
		return tests;
	}

	public TestSuite build(Map<String, Long> relevance) {
		String runtimeDescription = getSuiteDescription();
		TestSuite suite = new TestSuite("IntelliSuite - " + runtimeDescription);
		assignRelevance(relevance);
		addTests(suite);
		for (IntelliTestAdapter obj : this.testsToRun) {
			TestResult result = new TestResult();
			obj.run(result);
		}
		saveTestData();
		return suite;
	}
	
	public TestSuite build() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		relevance.put(RelevanceConstants.ERROR_RELEVANCE, 1L);
		relevance.put(RelevanceConstants.COVERAGE_RELEVANCE, 1L);
		relevance.put(RelevanceConstants.CLASSES_RELEVANCE, 1L);
		return this.build(relevance);
	}
	
	
	private void addTests (TestSuite suite) {
		boolean[] chosenTests = new AdaptSorterBuilder().chooseTests(this.testQueue, this.availableTimeMili, 
				this.importance);
		int i = 0;
		for (IntelliTestAdapter obj : this.testQueue) {
			if(chosenTests[i++])
				this.testsToRun.add(obj);
		}
	}
	
	private void saveTestData() {
		RetrieveCSVData csv = new RetrieveCSVData();
		csv.writer(this.testQueue);
	}
	
	private void assignRelevance(Map<String, Long> relevance) {
		Long errorValue = relevance.get(RelevanceConstants.ERROR_RELEVANCE);
		Long coverageValue = relevance.get(RelevanceConstants.COVERAGE_RELEVANCE);
		Long classesValue = relevance.get(RelevanceConstants.CLASSES_RELEVANCE);
		Long lastExecution = relevance.get(RelevanceConstants.LAST_EXECUTION_RELEVANCE);
		
		this.importance = new Long[4];
		
		if (errorValue != null)
			this.importance[0] = errorValue;
		else
			this.importance[0] = 1L;
		if (coverageValue != null)
			this.importance[1] = coverageValue;
		else
			this.importance[1] = 1L;
		if (classesValue != null)
			this.importance[2] = classesValue;
		else
			this.importance[2] = 1L;
		if (lastExecution != null)
			this.importance[3] = lastExecution;
		else
			this.importance[3] = 1L;
		
	}


	private String getSuiteDescription() {
		if(availableTimeMili == Long.MAX_VALUE)
			return "ALL";
		int seconds = (int)(availableTimeMili/1000);
		int minutes = seconds/60;
		if(seconds < 60)			
			return seconds + " seconds";
		return minutes+ " minutes";
	}
	
	
	public AdaptSuiteBuilder min(int minutes) {
		return sec(minutes * 60);
	}
	
	
	public AdaptSuiteBuilder sec(int seconds) {
		this.availableTimeMili = seconds * 1000;
		return this;
	}
	
	
	public static String getErrorConstant() {
		return RelevanceConstants.ERROR_RELEVANCE;
	}
	
	
	public static String getCoverageConstant() {
		return RelevanceConstants.COVERAGE_RELEVANCE;
	}
	
	
	public static String getClassConstant() {
		return RelevanceConstants.CLASSES_RELEVANCE;
	}
	
	public static String getLastExecutionConstant() {
		return RelevanceConstants.LAST_EXECUTION_RELEVANCE;
	}
}