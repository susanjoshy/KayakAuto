package com.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.util.Locators;

public class SearchPage {

	public WebDriver driver;
	
	By flightsLink = Locators.get("xpath-flightLink");
	By ori = Locators.get("id-origin");
	By destination = Locators.get("id-destination");
	By sDate = Locators.get("id-startDate");
	By eDate = Locators.get("id-endDate");
	By searchBtn = Locators.get("id-searchButton");
	By result = Locators.get("xpath-searchResult");
	By innerPrice = Locators.get("xpath-innerPrice");
	By resulOrigin = Locators.get("xpath-originResult");
	By resultDest = Locators.get("xpath-destResult");
	By takeOffTime = Locators.get("xpath-takeOff");
	By cabinDetails = Locators.get("xpath-cabin");
	
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void clickOnFlights()
	{
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
		driver.findElement(flightsLink).click();;
		/*String newWindow = driver.getWindowHandle();
        driver.switchTo().window(newWindow);*/
	}
	
	public Map<String,String> searchFlights(String origin,String dest,String startDate,String endDate,String data)
	{
		
		Map<String,String> flightDetails = new HashMap<>();
		try {
		
		driver.findElement(ori).sendKeys(origin);
		driver.findElement(destination).sendKeys(dest);
		driver.findElement(sDate).sendKeys(startDate);
		driver.findElement(eDate).sendKeys(endDate);
		driver.findElement(searchBtn).click();
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
		List<WebElement> elements = driver.findElements(result);
		for(WebElement element:elements)
		{
			WebElement innerElement = element.findElement(innerPrice);
			String price = innerElement.getText();
			if(price.equals(data))
			{
				element.click();
				
				flightDetails.put("resulOrigin",element.findElement(resulOrigin).getText());
				flightDetails.put("resultDest",element.findElement(resultDest).getText());
				
				String time = element.findElement(takeOffTime).getText();
				String cDetails = element.findElement(cabinDetails).getText();
				System.out.println("price : "+price +"Cabin details:" + cDetails + "Take Off Time" + time);
			}
		}
		System.out.println(elements.size());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightDetails;
	}
}
