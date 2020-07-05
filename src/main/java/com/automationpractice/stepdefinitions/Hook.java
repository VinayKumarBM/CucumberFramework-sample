package com.automationpractice.stepdefinitions;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.utilities.DriverManager;

import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	private static final Logger log = LoggerFactory.getLogger(Hook.class);
	
	@Before
	public void launchBrowser(Scenario scenario) {
		log.info("*****************************************************************************************\n");
		log.info("\t--{ Scenario: "+scenario.getName().toUpperCase()+" - STARTED }--\n");
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
			logError(scenario);
		}
		DriverManager.getInstance().closeDriver();
		log.info("*****************************************************************************************\n");
		log.info("\t--{ Scenario: "+scenario.getName().toUpperCase()+" - "+scenario.getStatus().toString().toUpperCase()+" }--\n");
		log.info("*****************************************************************************************");
	}
	
	private static void logError(Scenario scenario) {
		   Field field = FieldUtils.getField(scenario.getClass(), "stepResults", true);
		   field.setAccessible(true);
		   try {
		       for (Result result : (ArrayList<Result>) field.get(scenario)) {
		           if (result.getError() != null)
		               log.error("Scenario: {} => FAILED", scenario.getName(), result.getError());
		       }
		   } catch (Exception e) {
		       log.error("Error while logging error", e);
		   }
	}
}
