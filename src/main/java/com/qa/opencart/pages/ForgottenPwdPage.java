package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgottenPwdPage {
	
	private WebDriver driver;
	
	private By header =By.cssSelector("div#content h1");
	private By emailTextBox =By.name("email");
	private By continuebtn = By.xpath("//input[@value='Continue']");
	
	public ForgottenPwdPage(WebDriver driver) {
	
		this.driver =driver;
	}
	
	public String getForgottenPwdPageTitle() {
		return driver.getTitle();
	}

	public String getForgottenPwdPageUrl() {
		return driver.getCurrentUrl();
		
	}
	
	public String getForgottenpwdPageHeader() {
		return driver.findElement(header).getText();
		
	}
	
	public void enterEmailText(String emailid) {
		
		driver.findElement(emailTextBox).sendKeys(emailid);
		driver.findElement(continuebtn).click();
		
	
	}
	
	

}
