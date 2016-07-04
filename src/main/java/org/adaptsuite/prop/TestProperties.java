package org.adaptsuite.prop;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	private Properties properties;
	private File file;
	private String tmpDir = System.getProperty("java.io.tmpdir");

	public TestProperties(Class<?> clazz) {
		if(clazz != null) {
			String filename = tmpDir + File.separator + clazz.getName() + ".properties";
			file = new File(filename);
		}			 
	}

	private void init() {
		properties = new Properties();
		try {
			if (file == null || !file.exists())
				return;
			FileReader reader = new FileReader(file);
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String get(String property) {
		if(properties == null)
			init();
		return properties.getProperty(property);
	}

	public static TestProperties newInstance(Class<?> clazz) {
		return new TestProperties(clazz);
	}
	
	public void set(String key, Object value) {
		if(properties == null)
			init();
		properties.put(key, value.toString());
	}

	public void close() {
		try {
			FileWriter writer = new FileWriter(file);
			properties.store(writer, "test-properties");
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Long getLong(String key) {
		String value = get(key);
		if(value == null)
			return null;
		return Long.valueOf(value);
	}
	
	public Double getDouble(String key)
	{
		String value = get(key);
		if(value == null)
			return null;
		return Double.valueOf(value);
	}
}