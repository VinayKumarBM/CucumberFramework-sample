package com.automationpractice.stepdefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.framework.utilities.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	private final Log log = LogFactory.getLog(Hook.class);
	
	@Before
	public void launchBrowser(Scenario scenario) {
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{		-TEST STARTS-		}--");
		log.info("");
		log.info("*****************************************************************************************");
		log.info("Launching Browser");
		DriverManager.getInstance().launchBrowser(scenario);
	}

	@After
	public void closeBrowser(Scenario scenario) {
		if(scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getInstance().getDriver();
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			log.info("Completed taking screenshot of failed scenario");
		}
		log.info("Closing Browser");
		DriverManager.getInstance().closeDriver();
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{		-TEST "+scenario.getStatus().toString().toUpperCase()+"-		}--");
		log.info("");
		log.info("*****************************************************************************************");
	}
}
