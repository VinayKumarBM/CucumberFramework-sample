package com.framework.utilities;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.Scenario;


public class DriverManager {
	private final Log log = LogFactory.getLog(DriverManager.class);
	private static DriverManager driverManager = new DriverManager();
	private WebDriver driver;
	private DriverType driverType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
//	private Eyes eyes;

	private DriverManager() {
		driverType = getBrowser();
//		eyes = new Eyes();
//		eyes.setApiKey(GetConfig.getConfigProperty("API_KEY"));
	}

	public static DriverManager getInstance() {
		return driverManager;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
/*
	public Eyes getEyes() {
		return eyes;
	}
	
*/	public WebDriver launchBrowser(Scenario scenario) {	
		String driverPath = System.getProperty("user.dir")+GetConfig.getConfigProperty("browserDriverPath")+"%s";
		log.info("Launching "+driverType+" browser.");
		switch (driverType) {     
		case FIREFOX : 
			System.setProperty(CHROME_DRIVER_PROPERTY, String.format(driverPath, GetConfig.getConfigProperty("firefoxDriver")));
			driver = new FirefoxDriver();
			break;
		case CHROME : 
			System.setProperty(CHROME_DRIVER_PROPERTY, String.format(driverPath, GetConfig.getConfigProperty("chromeDriver")));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
		//	options.addArguments("--start-maximized");
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(options);
			break;
		case INTERNETEXPLORER : 
			System.setProperty(CHROME_DRIVER_PROPERTY, String.format(driverPath, GetConfig.getConfigProperty("ieDriver")));
			driver = new InternetExplorerDriver();
			break;
		}
//		String[] sourceTag = scenario.getSourceTagNames().toArray(new String[0]);
//		eyes.open(driver, sourceTag[0], scenario.getName());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(GetConfig.getConfigProperty("implicitWaitTime")), TimeUnit.SECONDS);
		return driver;
	}

	public DriverType getBrowser() {
		String browserName = GetConfig.getConfigProperty("browser");
		if(browserName == null || browserName.equals("chrome")) 
			return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) 
			return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) 
			return DriverType.INTERNETEXPLORER;
		else 
			throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public void closeDriver() {
		log.info("Closing Browser");
		driver.quit();
//		eyes.close();
//		eyes.abortIfNotClosed();
	}
}
