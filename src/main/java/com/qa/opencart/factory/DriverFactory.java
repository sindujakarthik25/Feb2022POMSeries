package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.customexception.FrameWorkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 *@author Sinduja 
 *
 */

public class DriverFactory {
	
	/**
	 *  this method is used to initialize the driver on basis of given browserName
	 * 
	 */
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * this method is used to initialize the driver on the basis of given
	 * browser name
	 * 
	 * @param properties prop
	 * @return this method will return the webDriver
	 */
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		System.out.println("browser name is"+browserName);
		
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new edgeDriver(optionsManager.getedgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			
		
			
		}
		else {
			System.out.println("please pass the right browser name"+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is used to initialize the properties form the respective config file
	 * 
	 * @return this will return properties class object with all the config properties
	 */
	
	
	
	
	public Properties init_prop() {
		FileInputStream ip =null;
		prop= new Properties();
		
		//maven command line arg:
		//mvn clean install -Denv="qa"
		
		String envName = System.getProperty("env");
		System.out.println("Running test on environment:"+envName);
		
		if(envName == null) {
			System.out.println("No env is given... hence running it on QA");
			try {
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
			
			switch (envName.toLowerCase()) {
			case "qa":
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip=new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "prod":
				ip=new FileInputStream("./src/test/resources/config/config.properties");
				break;	
			default:
				
				System.out.println("Please pass the right environment value:"+envName);
				throw new FrameWorkException("no env found...");
				
			}
			
			}catch(FileNotFoundException e)	{
				e.printStackTrace();
			} catch (FrameWorkException e) {
				e.printStackTrace();
			}
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return prop;
	}
	
	//take screenshot
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
		
	}
	
	
	

