package org.intellitesting.prop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.intellitesting.adapter.Level;

public class IntelliProperties {	

	private Properties properties;

	public IntelliProperties() {
		properties = new Properties();
		String resourceName = "/intellitesting.properties";
		URL resource = getClass().getResource(resourceName);
		if(resource == null)
			throw new IllegalStateException("Resource \"" + resourceName + "\" not found!");
		try {
			InputStream input = getClass().getResourceAsStream(resourceName);			
			properties.load(input);
			input.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Level> getLevelsUpTo(Integer runtimeLevel) {
		String[] durationProps = get("intellitesting.durations").split(",");		
		List<Level> levels = new ArrayList<Level>();
		for(int i = 0; i <= Math.min(durationProps.length-1, runtimeLevel-1); i++){			
			levels.add(new Level(durationProps[i]));
		}
		return levels;
	}

	private String get(String key) {
		return properties.getProperty(key);
	}

	public String getTestInfoDir() {
		return System.getProperty("java.io.tmpdir");
	}

}
