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
import main.java.org.adaptsuite.sorter.SuiteSorter;
import main.java.org.adaptsuite.sorter.GluttonySuiteSorter;
import main.java.org.adaptsuite.sorter.RandomSuiteSorter;
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
	private TestSuite suite;
	

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
	
	private void abstractBuild(Map<String, Long> relevance) {
		String runtimeDescription = getSuiteDescription();
		this.suite = new TestSuite("IntelliSuite - " + runtimeDescription);
		assignRelevance(relevance);
	}

	public TestSuite build(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addTests(false);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite buildReverse(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addTests(true);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite build() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.build(relevance);
	}
	
	public TestSuite buildReverse() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.buildReverse(relevance);
	}
	
	public TestSuite randomBuild(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addRandomTests(false);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite randomBuildReverse(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addRandomTests(true);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite randomBuild() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.randomBuild(relevance);
	}
	
	public TestSuite randomBuildReverse() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.randomBuildReverse(relevance);
	}
	
	
	public TestSuite gluttonyBuild(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addGluttonyTests(false);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite gluttonyBuildReverse(Map<String, Long> relevance) {
		this.abstractBuild(relevance);
		this.addGluttonyTests(true);
		this.runTests();
		return this.suite;
	}
	
	public TestSuite gluttonyBuild() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.gluttonyBuild(relevance);
	}
	
	public TestSuite gluttonyBuildReverse() {
		Map <String, Long> relevance = new HashMap<String, Long>();
		return this.gluttonyBuild(relevance);
	}
	
	private void runTests() {
		for (IntelliTestAdapter obj : this.testsToRun) {
			TestResult result = new TestResult();
			obj.run(result);
		}
		saveTestData();
	}
	
	
	private void addTests (boolean isReverse) {
		boolean[] chosenTests = new SuiteSorter().chooseTests(this.testQueue, this.availableTimeMili, 
				this.importance, isReverse);
		int i = 0;
		for (IntelliTestAdapter obj : this.testQueue) {
			if(chosenTests[i++])
				this.testsToRun.add(obj);
		}
	}
	
	private void addRandomTests(boolean isReverse) {
		boolean[] chosenTests = new RandomSuiteSorter().chooseTests(this.testQueue, this.availableTimeMili, 
				this.importance, isReverse);
		int i = 0;
		for (IntelliTestAdapter obj : this.testQueue) {
			if(chosenTests[i++])
				this.testsToRun.add(obj);
		}
	}
	
	private void addGluttonyTests(boolean isReverse) {
		boolean[] chosenTests = new GluttonySuiteSorter().chooseTests(this.testQueue, this.availableTimeMili, 
				this.importance, isReverse);
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
		Long lastExecution = relevance.get(RelevanceConstants.LAST_EXECUTION_RELEVANCE);
		Long frequency = relevance.get(RelevanceConstants.FREQUENCY_RELEVANCE);
		Long failFrequency =  relevance.get(RelevanceConstants.FAILURE_FREQUENCY_RELEVANCE);
		
		this.importance = new Long[5];
		
		if (errorValue != null)
			this.importance[0] = errorValue;
		else
			this.importance[0] = 1L;
		if (coverageValue != null)
			this.importance[1] = coverageValue;
		else
			this.importance[1] = 1L;
		if (lastExecution != null)
			this.importance[2] = lastExecution;
		else
			this.importance[2] = 1L;
		if (frequency != null)
			this.importance[3] = frequency;
		else
			this.importance[3] = 1L;
		if (failFrequency != null)
			this.importance[4] = failFrequency;
		else
			this.importance[4] = 1L;
		
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
	
	public AdaptSuiteBuilder mili(int miliseconds) {
		this.availableTimeMili = miliseconds;
		return this;
	}
	
	
	public static String getErrorConstant() {
		return RelevanceConstants.ERROR_RELEVANCE;
	}
	
	
	public static String getCoverageConstant() {
		return RelevanceConstants.COVERAGE_RELEVANCE;
	}
	
	
	public static String getLastExecutionConstant() {
		return RelevanceConstants.LAST_EXECUTION_RELEVANCE;
	}
	
	public static String getFrequencyConstant() {
		return RelevanceConstants.FREQUENCY_RELEVANCE;
	}
	
	public static String getFailFrequencyConstant() {
		return RelevanceConstants.FAILURE_FREQUENCY_RELEVANCE;
	}
}