package com.qa.opencart.tests;



import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.util.DescriptionConstants;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		commPage = new CommonsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
		
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
		};
	}
	
	@Test(dataProvider="getProductData")
	public void productImageCountTest(String searchKey, String productName, int imagesCount ) {
		searchResultsPage = commPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProductName(productName);
        Assert.assertEquals(productInfoPage.getProductImageCount(),imagesCount);
	}
	@Test
	public void productDescriptionTest() {
		searchResultsPage = commPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Air");
		String productDesc = productInfoPage.getProductDescription();
		System.out.println("Product desc: "+productDesc);
		
		softAssert.assertTrue(productDesc.contains("Macbook Air"));
		softAssert.assertTrue(productDesc!=null);
		softAssert.assertFalse(productDesc.isEmpty());
		
		softAssert.assertTrue(productDesc.contains(DescriptionConstants.MACBOOK_AIR_DESCRIPTION));
		softAssert.assertAll();
		
	}
	@Test
	public void productDataTest() {
		
		searchResultsPage = commPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Air");
		Map<String,String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v)-> System.out.println(k+":"+v));//Brand:Apple
		
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(actProductInfoMap.get("name"),"MacBook Air");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"),"700");
		softAssert.assertAll();
		
		
//		HashMap:
		
//	
//		Brand: Apple
//		Availability: In Stock
//	    MacBook Air	
//		Reward Points: 700
//		
//		
		
		
		
//		LinkedHashMAp:
		
//		MacBook Air
//		Brand: Apple
//		Product Code: Product 17
//		Reward Points: 700
//		Availability: In Stock
//		$1,202.00
//		Ex Tax: $1,000.00		
		
		
	}

}
