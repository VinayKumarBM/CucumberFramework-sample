package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage {
	private static final Logger log = LoggerFactory.getLogger(MyAccountPage.class);
	private WebDriver driver;
	
	@FindBy(css = ".page-heading")
	WebElement pageHeading;

	@FindBy(css = "a.account>span")
	WebElement userName;
	
	@FindBy(css = ".logout")
	WebElement logoutButton;
	
	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public String getPageHeading() {
		log.info("Page headding is: "+pageHeading.getText());
		return pageHeading.getText().trim();
	}
	
	public String getUserName() {
		log.info("Logged in user is: "+userName.getText());
		return userName.getText().trim();
	}
	
	public void logout() {
		logoutButton.click();
		log.info("Clicked on Logout Button");
	}

}
