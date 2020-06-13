package com.automationpractice.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.utilities.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	private static final Logger log = LoggerFactory.getLogger(Hook.class);
	
	@Before
	public void launchBrowser(Scenario scenario) {
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{		-TEST STARTS-		}--");
		log.info("");
		log.info("*****************************************************************************************");
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
		DriverManager.getInstance().closeDriver();
		log.info("*****************************************************************************************");
		log.info("");
		log.info("\t\t\t--{		-TEST "+scenario.getStatus().toString().toUpperCase()+"-		}--");
		log.info("");
		log.info("*****************************************************************************************");
	}
}
