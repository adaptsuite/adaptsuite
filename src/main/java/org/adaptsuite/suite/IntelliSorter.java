package org.adaptsuite.suite;

import java.util.Comparator;

import org.adaptsuite.adapter.IntelliTestAdapter;

public class IntelliSorter implements Comparator<IntelliTestAdapter>{	

	public int compare(IntelliTestAdapter t1, IntelliTestAdapter t2) {
		return t1.getTime().compareTo(t2.getTime());
	}

}
