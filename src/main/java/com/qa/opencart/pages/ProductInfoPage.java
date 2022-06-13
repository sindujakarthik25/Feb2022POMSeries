package com.qa.opencart.pages;

import java.util.HashMap;


import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By mainProductName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productDescription = By.cssSelector("div#tab-description");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");


	public  ProductInfoPage (WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(this.driver);
	}
	public String getMainProductName() {
		return eleutil.doGetElementText(mainProductName);
	}
	public int getProductImageCount() {
		return eleutil.waitForElementsVisible(productImages,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).size();
	}
	public String getProductDescription() {
		return eleutil.doGetElementText(productDescription);
	}
	public Map<String, String> getProductInfo() {
		Map<String, String>productInfoMap = new HashMap<String, String>();//random order
		//Map<String, String>productInfoMap = new LinkedHashMap<String, String>();//order based
		//Map<String, String>productInfoMap = new TreeMap<String, String>();//sorted order based
		
		
		productInfoMap.put(null,"TestAutomation");
		productInfoMap.put("name",getMainProductName());
		
		//fetching MetaData:
		
		List<WebElement> metaList = eleutil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaVal = metaData.split(":")[1].trim();
			
			productInfoMap.put(metaKey, metaVal);
		}
		
		//price data:
		
		List<WebElement> priceList = eleutil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		productInfoMap.put("price",price);
		productInfoMap.put("exTaxPrice",exTaxPrice);
		return productInfoMap;
	}

}
