package com.automationpractice.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.utilities.GetConfig;
import com.rajatthareja.reportbuilder.Color;
import com.rajatthareja.reportbuilder.ReportBuilder;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/"},
        glue = {"com.automationpractice.stepdefinitions"},
        monochrome = true,
        plugin = {"pretty", "html:target/generated-reports/cucumber-report",
                "json:target/generated-reports/CucumberTestReport.json",
                "usage:target/generated-reports/cucumber-usage.json", 
                "junit:target/generated-reports/cucumber-results.xml",
                "rerun:target/generated-reports/rerun.txt"},
        snippets = SnippetType.CAMELCASE,
        //dryRun = true,
        tags = {"@compare"}	
		)
public class FeatureRunner {
	private static final Logger log = LoggerFactory.getLogger(FeatureRunner.class);
	
	@AfterClass
	public static void generateHTMLReport() {
		log.info("Started generating html report...");
		// Create ReportBuilder Object
        ReportBuilder reportBuilder = new ReportBuilder();        
        // Set output Report Dir 
        reportBuilder.setReportDirectory("target/generated-reports/detailed-report/");        
        // Set output report file name
        reportBuilder.setReportFileName("index");        
        // Set Report Title
        reportBuilder.setReportTitle("Automation Test Result");        
        // Set Report Color
        reportBuilder.setReportColor(Color.INDIGO);        
        // Enable voice control for report
        reportBuilder.disableVoiceControl();        
        
        // Add additional info for Report
        reportBuilder.setAdditionalInfo("Operating System", "Windows 7");
        reportBuilder.setAdditionalInfo("Browser", GetConfig.getConfigProperty("browser"));
        reportBuilder.setAdditionalInfo("Environment", GetConfig.getConfigProperty("base.url"));

        // Create list or report Files or Directories or URLs or JSONObject or JSONString
        List<Object> cucumberJsonReports = new ArrayList<>();
        cucumberJsonReports.add(new File("target/generated-reports/CucumberTestReport.json"));        
        // Build your report
        reportBuilder.build(cucumberJsonReports);
        log.info("Completed generating html report!!");
	}
	
}