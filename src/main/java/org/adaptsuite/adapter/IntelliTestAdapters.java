package main.java.org.adaptsuite.adapter;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class IntelliTestAdapters extends ArrayList<IntelliTestAdapter>{
	
	public IntelliTestAdapters(Class<?>[] tests) {
		for (Class<?> test : tests) {
			add(new IntelliTestAdapter(test, getName(test)));
		}
	}
	
	private String getName (Class <?> test)
	{
		String path = test.getName();
		String name[] = path.split("\\.");
		
		return name[name.length - 1];
		
	}

}
