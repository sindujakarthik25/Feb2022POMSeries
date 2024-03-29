package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By header = By.cssSelector("div#logo h1 a");
	private By accountSectionsHeader = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	
	public AccountsPage (WebDriver driver) {
		
		this.driver = driver;
		
		eleutil = new ElementUtil(this.driver);
		
	}
	
	public String getAccPageTitle() {
		
		return eleutil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccPageUrl() {
		return eleutil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
		
	}
	public String getAccPageHeader() {
	
		return eleutil.waitForElementVisible(header,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		
	}
	public List<String> getAccountSectionsList() {
		List<WebElement> accSecList = eleutil.waitForElementsVisible(accountSectionsHeader,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
	
	public boolean isLogoutLinkExist() {
		return eleutil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage  clickOnLogout() {
		if(isLogoutLinkExist()) {
				eleutil.doClick(logoutLink);
				return new LoginPage(driver);
	}
		return null;
	}
	
	public boolean issearchExist() {
		return eleutil.doIsDisplayed(search);
	}

}
