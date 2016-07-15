package com.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Configuration {

	public  String BROWSER = "";
	public  String URL = "";
	
	public Configuration()
	{
		loadProperties();
		//startBrowser();
	}
	public void loadProperties()
	{
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader(String.format("%s/config.properties", System.getProperty("user.dir"))));
			BROWSER = prop.getProperty("browser");
			URL = prop.getProperty("url");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver startBrowser()
	{
		WebDriver  driver = null;
		 try {
	            switch (BROWSER) {
	                case "ie":
	                	driver = startInternetExplorer();
	                    break;
	                case "firefox":
	                	driver = startFirefox();
	                    break;
	                case "chrome":
	                	driver = startChrome();
	                    break;
	                case "html":
	                	driver = startHtmlUnit();
	                    break;
	                default:
	                	driver = startHtmlUnit();
	                    break;
	            }
	            driver.get(URL);
	            if (!BROWSER.equals("html")) {
	            	driver.manage().window().maximize();
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		 
		 return driver;
	}
	
	private InternetExplorerDriver startInternetExplorer() {
        System.setProperty("webdriver.ie.driver", String.format("%s/IEDriverServer.exe", System.getProperty("user.dir")));
        return new InternetExplorerDriver();
    }

    private FirefoxDriver startFirefox() {
        return new FirefoxDriver();
    }

    private ChromeDriver startChrome() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/chromedriver.exe", System.getProperty("user.dir")));
        return new ChromeDriver();
    }

    private HtmlUnitDriver startHtmlUnit() {
        return new HtmlUnitDriver();
    }
}
