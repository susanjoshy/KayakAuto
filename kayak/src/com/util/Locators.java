package com.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

public class Locators {
	
    private static HashMap<String, String> _locators;
    
    public static HashMap<String, String> get_locators() {
		return _locators;
	}

	public static void loadLocators() {
        if (_locators != null)
            return;
        _locators = new HashMap<String, String>();
Properties prop = new Properties();
		
		try {
			prop.load(new FileReader(String.format("%s/locators.properties", System.getProperty("user.dir"))));
			
			for(Object key : prop.keySet())
			{
				String k = (String)key;
				_locators.put(k, prop.getProperty(k));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static String getValue(String key) {
        try {
            return _locators.get(key);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return "";
    }

    public static By get(String locator){
        try {
            if (locator.contains("-")) {
                String[] values = locator.split("-");
                if (values[0].toLowerCase().equals("css")) {
                    return By.cssSelector(getValue(locator));
                }
                if (values[0].toLowerCase().equals("xpath")) {
                    return By.xpath(getValue(locator));
                }
                if (values[0].toLowerCase().equals("id")) {
                    return By.id(getValue(locator));
                }
                if (values[0].toLowerCase().equals("name")) {
                    return By.name(getValue(locator));
                }
                if (values[0].toLowerCase().equals("class")) {
                    return By.className(getValue(locator));
                }
                if (values[0].toLowerCase().equals("link")) {
                    return By.linkText(getValue(locator));
                }
                if (values[0].toLowerCase().equals("plink")) {
                    return By.partialLinkText(getValue(locator));
                }
                if (values[0].toLowerCase().equals("tag")) {
                    return By.tagName(getValue(locator));
                }
                return By.cssSelector(getValue(locator));
            }

           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return By.cssSelector(getValue(locator));
    }
}
