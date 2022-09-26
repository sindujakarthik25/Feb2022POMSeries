package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;

	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public CommonsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(this.driver);
		
	}
	
	public SearchResultPage doSearch(String productName ) {
		
		WebElement SearchEle = eleutil.waitForElementVisible(search,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		SearchEle.clear();
		SearchEle.sendKeys(productName);
		eleutil.doClick(searchIcon);
		return new SearchResultPage(this.driver);
	}
}