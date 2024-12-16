package com.testng.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	FileInputStream fileInput;
	
	public String getProperties(String key) {
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "\\properties\\application.properties";
		
		try {
			fileInput = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String value = prop.getProperty(key);

		return value;
	}
}