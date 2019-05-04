package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
	private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver driver;
	
	@FindBy(name = "email_create")
	WebElement createAccountEmail;
	
	@FindBy(name ="SubmitCreate")
	WebElement createAccount_Button;
	
	@FindBy(name ="email")
	WebElement registeredEmail;
	
	@FindBy(name ="passwd")
	WebElement password;
	
	@FindBy(name ="SubmitLogin")
	WebElement login_Button;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void enterEmailToCreateAccount(String email) {
		log.info("Entering from Login Page");
		//createAccountEmail.clear();
		createAccountEmail.sendKeys(email);
	}
	
	public void clickOnCreateAccountButton() {
		createAccount_Button.click();
	}

	public void enterEmailToSignin(String email) {
		registeredEmail.clear();
		registeredEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		login_Button.click();
	}
	
}
