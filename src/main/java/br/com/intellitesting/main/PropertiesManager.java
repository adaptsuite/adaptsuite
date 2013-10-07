package br.com.intellitesting.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

	private Properties properties;
	private File file;

	public PropertiesManager(Class<?> clazz) {
		if(clazz != null)
			file = new File(clazz.getName() + ".properties");			 
	}

	private void init() {
		properties = new Properties();
		InputStream mainProperties = this.getClass().getResourceAsStream("/intellitesting.properties");
		try {
			properties.load(mainProperties);
			if(file == null || !file.exists())
				return;			
			FileReader reader = new FileReader(file);
			properties.load(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String get(String property) {
		if(properties == null)
			init();
		return properties.getProperty(property);
	}

	public static PropertiesManager newInstance(Class<?> clazz) {
		return new PropertiesManager(clazz);
	}
	
	public static PropertiesManager newInstance() {
		return new PropertiesManager(null);
	}

	public void set(String key, Object value) {
		if(properties == null)
			init();
		properties.put(key, value.toString());
	}

	public void close() {
		if(file == null)
			return;
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
}