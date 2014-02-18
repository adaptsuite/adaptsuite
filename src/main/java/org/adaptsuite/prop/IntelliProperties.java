package org.adaptsuite.prop;
//package org.intellitesting.prop;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import org.intellitesting.adapter.Level;
//
//public class IntelliProperties {	
//
//	private Properties properties;
//
//	public IntelliProperties() {
//		properties = new Properties();
//		String resourceName = "/intellitesting.properties";
//		URL resource = getClass().getResource(resourceName);
//		if(resource == null)
//			throw new IllegalStateException("Resource \"" + resourceName + "\" not found!");
//		try {
//			InputStream input = getClass().getResourceAsStream(resourceName);			
//			properties.load(input);
//			input.close();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	private String get(String key) {
//		return properties.getProperty(key);
//	}
//
//	public String getTestInfoDir() {
//		return System.getProperty("java.io.tmpdir");
//	}
//
//}
