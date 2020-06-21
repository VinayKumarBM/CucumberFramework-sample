package com.automationpractice.stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pages.ContactUsPage;
import com.automationpractice.pages.HomePage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.When;

public class ContactUsStepDefinition {
	private HomePage homePage;
	private WebDriver driver;
	private ContactUsPage contactUsPage;
	
	public ContactUsStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		homePage = new HomePage(driver);
		contactUsPage = new ContactUsPage(driver);
	}

	@When("I navigate to Contact Us page")
	public void iNavigateToContactUsPage() {
	    homePage.clickOnContactUsLink();
	}

	@When("I send a message '(.*)', '(.*)', '(.*)', '(.*)', '(.*)'")
	public void iSendAMessage(String subjectHeading, String headingDesc, String email, String reference, String message) {
	    contactUsPage.enterMessageDetails(subjectHeading, email, reference, message);
	    assertEquals("Subject Heading did not match ", headingDesc, contactUsPage.getSubejctDescrption());
	    contactUsPage.sendMessage();
	}
	
}
