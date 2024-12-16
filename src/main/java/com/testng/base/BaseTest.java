package com.testng.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.testng.utilities.PropertiesReader;

import io.github.bonigarcia.wdm.WebDriverManager;

//common methods across all tests

public class BaseTest {
	WebDriver driver;
	PropertiesReader properties;
	String browserName, url;
	
	public WebDriver getDriver()
	{	
		if (driver == null)
		{
			properties = new PropertiesReader();
			browserName = properties.getProperties("browser");
			url = properties.getProperties("url");
			
			if (browserName.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if (browserName.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		
		driver.get(url);
		
		return driver;
	}
	
	public void closeBrowser()
	{
		driver.close();
		driver = null;
	}
}
