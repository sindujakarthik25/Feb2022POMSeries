package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//1.by locators
	
private	WebDriver driver;
private ElementUtil eleutil;
	
private	By emailId = By.id("input-email");
private	By password = By.id("input-password");
private	By loginbtn = By.xpath("//input[@value='Login']");
private	By forgotPwdLink = By.linkText("Forgotten Password");
private	By registerLink =By.linkText("Register");
private By accountLogoutMessage =By.cssSelector("div#content h1");
	
	//2.public page class constructor
	
	public LoginPage(WebDriver driver) {
		
		this.driver= driver;
		eleutil = new ElementUtil(this.driver);
		
	}
	
	//3 public page actions/method
	
	
	@Step("getting login page title....")
	public String getLoginPageTitle() {
		
		String title = eleutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
		System.out.println("login page title is:"+title);
		return title;
	}
	
	
	@Step("getting login page url....")
	public String getLoginPageUrl() {
		String url = eleutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
		System.out.println("login page url is:"+url);
		return url;
	}
	
	@Step("checking forgot pwd link exist or not....")
	public boolean isForgotPwdLinkExist() {
		return eleutil.waitForElementVisible(forgotPwdLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
		
	}
	
	@Step("checking forgot pwd link exist or not....")
	public void clickForgottenPwdLink() {
		
		driver.findElement(forgotPwdLink).click();
	}
	@Step("checking register link exist or not....")
	public boolean isRegisterLinkExist() {
	    return eleutil.waitForElementVisible(registerLink,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	    
	}
	
	@Step("login with username {0} and password {1}")
	public AccountsPage doLogin(String userName, String pwd) {
	 eleutil.waitForElementVisible(emailId,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(userName);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginbtn);
		
		return new AccountsPage(driver);
		
	}
	
	@Step("getting success messg after logout")
	public String getLogoutMessage() {
		String logoutMessage =eleutil.waitForElementVisible(accountLogoutMessage,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		System.out.println("Logout successful message =="+logoutMessage);
		return logoutMessage;
		
	}
	
	
	@Step("navigating to register page...")
	public RegisterPage navigateToRegisterPage() {
		 eleutil.doClick(registerLink);
		 return new RegisterPage(driver);
	}

}
