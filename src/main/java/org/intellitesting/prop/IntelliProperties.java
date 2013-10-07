package org.intellitesting.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.intellitesting.model.Level;

public class IntelliProperties {	

	private Properties properties;

	public IntelliProperties() {
		properties = new Properties();
		InputStream input = this.getClass().getResourceAsStream("/intellitesting.properties");
		try {
			properties.load(input);
			input.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Level> getLevelsUpTo(Integer runtimeLevel) {		
		String[] levelProps = get("intellitesting.levels").split(",");
		String[] durationProps = get("intellitesting.durations").split(",");		
		List<Level> levels = new ArrayList<Level>();
		for(int i = 0; i <= Math.min(levelProps.length-1, runtimeLevel-1); i++){			
			levels.add(new Level(levelProps[i],durationProps[i]));
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
