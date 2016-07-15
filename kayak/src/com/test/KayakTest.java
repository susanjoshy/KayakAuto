package com.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.util.Configuration;
import com.util.Locators;

public class KayakTest {
	
	WebDriver driver;
	
	 Configuration config;
	
	public KayakTest()
	{
		this.config = new Configuration();
		this.driver = config.startBrowser();
	}
	
	@BeforeSuite
	public void beforeSuite()
	{
		Locators.loadLocators();
	}
	
	public void afterSuite()
	{
		driver.quit();
	}

}
