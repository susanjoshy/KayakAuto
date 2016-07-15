package com.test;

import static org.testng.AssertJUnit.assertEquals;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.SearchPage;
import com.util.DataUtil;

public class SearchPageTest extends KayakTest{

	
	SearchPage sp;
	static DataUtil du;
	
	@BeforeClass
	public void beforeClass()
	{
		
		sp = new SearchPage(driver);
		sp.clickOnFlights();
		System.out.println("Flights has been loaded.");
		this.du = new DataUtil();
	}
	
	@DataProvider(name="search-data")
	public static Object[][] getData()
	{
		Object[][] data = du.read();
		System.out.println("data set is "+data );
		return data;
	}
	
	
	@Test(dataProvider="search-data")
	public void testSearchResult(String origin,String dest,String startDate,String endDate,String data)
	{
		Map<String,String> actual = sp.searchFlights(origin,dest,startDate,endDate,data);
		System.out.println("Origin in result" + actual.get("resulOrigin")
		+"destination in result" + actual.get("resultDest"));
		assertEquals(origin,actual.get("resulOrigin"));
		assertEquals(dest,actual.get("resultDest"));
	}
}
