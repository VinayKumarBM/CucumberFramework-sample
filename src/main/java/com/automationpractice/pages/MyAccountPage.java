package com.automationpractice.pages;

import org.openqa.selenium.By;
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
	private WebElement pageHeading;

	@FindBy(css = "a.account>span")
	private WebElement userName;
	
	@FindBy(css = ".logout")
	private WebElement logoutButton;
	
	@FindBy(css = "a[title='View my customer account']>span")
	private WebElement myAccountLink;
	
	@FindBy(xpath = "//li[1]/a[contains(@class,'button')]/span")
	private WebElement backToMyAccountButton;
	
	@FindBy(xpath = "//li[2]/a[contains(@class,'button')]/span")
	private WebElement homeButton;

	private final String myAccountPagesXpath = "//span[text()='%s']";
	
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
	
	public void navigateToMyAccount() {
		myAccountLink.click();
		log.info("Navigated to My Account page");
	}

	public void navigateToPageInMyAccount(String page) {
		driver.findElement(By.xpath(String.format(myAccountPagesXpath, page))).click();
		log.info("Navigate to "+page+" page");
	}
	
	public void clickBackToYourAccountButton() {
		backToMyAccountButton.click();
		log.info("Clicked on Back to Your Account button");
	}
	
	public void clickHomeButton() {
		homeButton.click();
		log.info("Clicked on Home button");
	}
}
