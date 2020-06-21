package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.JsonReader;
import com.automationpractice.models.CreateAccount;
import com.automationpractice.pages.CreateAccountPage;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MyAccountPage;
import com.framework.utilities.DriverManager;
import com.framework.utilities.TestScenario;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateAccoutStepDefinition {
	private static final Logger log = LoggerFactory.getLogger(CreateAccoutStepDefinition.class);
	private WebDriver driver;
	private HomePage homePage;	
	private LoginPage loginPage;	
	private CreateAccountPage createAccountPage;	
	private MyAccountPage myAccountPage;
	
	public CreateAccoutStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		createAccountPage = new CreateAccountPage(driver);
		myAccountPage = new MyAccountPage(driver);		
	}
	
	@When("^I navigate to Login Page by clicking on Sign In button$")
	public void i_navigate_to_Login_Page_by_clicking_on_Sign_In_button() {
		homePage.clickOnSignInLink();
	}

	@When("^I enter valid email id to create an account$")
	public void i_enter_valid_email_id_and_click_on_create_account_button() {
		long randNumb = System.currentTimeMillis();
		String email = String.format("test%s@gmail.com", randNumb);
	    loginPage.enterEmailToCreateAccount(email);
	    loginPage.clickOnCreateAccountButton();
	    log.info("Email to create account: "+email);
	    TestScenario.getSession().setVariable("email", email);
	}

	@When("^I enter valid details (.*) and register the user$")
	public void i_enter_valid_details_and_register_the_user(String dataKey) {
	   JsonReader json = new JsonReader();
	   CreateAccount createAccount = json.getpageByFirstName(dataKey);
	   assertEquals("email used for registering did not match", TestScenario.getSession().getVariable("email"), createAccountPage.getEmailUsedForRegistering());
	   TestScenario.getSession().setVariable("userName", createAccount.createNewAccount.firstName+" "+createAccount.createNewAccount.lastName);
	   createAccountPage.createAccount(createAccount);
	}

	@Then("^I should be navigated to (.*) screen$")
	public void i_should_be_navigated_to_screen(String pageTitle) {
		assertEquals("Page heading is Not correct", pageTitle.toUpperCase(), myAccountPage.getPageHeading());		
		log.info("Page Title is: "+driver.getTitle());
	}	
	
	@When("I logout of application")
	public void iLogoutOfApplication() {
		myAccountPage.logout();
		assertTrue("Logout was not successfull", homePage.isSignInLinkDisplayed());
	}
}
