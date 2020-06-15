package com.framework.utilities;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;


public class DriverManager {
	private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
	private static DriverManager driverManager;
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	public static final String USERNAME = "bmvinayk";
	public static final String AUTOMATE_KEY = "adsadWewcasdhf";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	//	private Eyes eyes;

	private DriverManager() {
		//		eyes = new Eyes();
		//		eyes.setApiKey(GetConfig.getConfigProperty("API_KEY"));
	}

	public static DriverManager getInstance() {
		if(driverManager == null) {
			synchronized (DriverManager.class) {
				if(driverManager == null) {
					driverManager  = new DriverManager();
				}
			}			
		}
		return driverManager;
	}

	public synchronized WebDriver getDriver() {
		WebDriver driver = tDriver.get();
		if (driver == null) {
			throw new IllegalStateException("Driver should have not been null!!");
		}
		return driver;
	}
	/*
	public Eyes getEyes() {
		return eyes;
	}
	 */	
	public synchronized WebDriver launchBrowser(Scenario scenario) throws MalformedURLException {	
		WebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();        
		caps.setCapability("os", GetConfig.getConfigProperty("os"));
		caps.setCapability("os_version", GetConfig.getConfigProperty("os_version"));
		caps.setCapability("resolution", "1024x768");
		caps.setCapability("browser", GetConfig.getConfigProperty("browser"));
	    caps.setCapability("browser_version", GetConfig.getConfigProperty("browser_version"));
	    caps.setCapability("name", "BrowserStack "+GetConfig.getConfigProperty("browser")+" Test");
		
		log.info("Launching "+GetConfig.getConfigProperty("browser")+" browser.");
		driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		//		String[] sourceTag = scenario.getSourceTagNames().toArray(new String[0]);
		//		eyes.open(driver, sourceTag[0], scenario.getName());
		tDriver.set(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(GetConfig.getConfigProperty("implicitWaitTime")), TimeUnit.SECONDS);
		return driver;
	}

	public synchronized void closeDriver() {
		log.info("Closing Browser");
		DriverManager.getInstance().getDriver().quit();
		//		eyes.close();
		//		eyes.abortIfNotClosed();
	}
}