package com.automationpractice.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage {
	private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver driver;
	
	@FindBy(css = ".page-heading")
	WebElement pageHeading;

	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void validatesUserIsInMyAccountsPage() {
		Assert.assertTrue("Page Title is Not correct", driver.getTitle().contains("My account"));
		Assert.assertEquals("Page heading is Not correct", "MY ACCOUNT", pageHeading.getText().trim());
	}

}
