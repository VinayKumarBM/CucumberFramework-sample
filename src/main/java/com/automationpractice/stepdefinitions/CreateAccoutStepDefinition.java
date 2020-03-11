package com.automationpractice.stepdefinitions;

import org.junit.Assert;
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
	private TestScenario scenario;
	
	public CreateAccoutStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		scenario = TestScenario.getScenario();
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
	    scenario.setSessionVariable("email", email);
	}

	@When("^I enter valid (.*) and register the user$")
	public void i_enter_valid_first_and_register_the_user(String dataKey) {
	   JsonReader json = new JsonReader();
	   CreateAccount createAccount = json.getpageByFirstName(dataKey);
	   createAccountPage.createAccount(createAccount);
	}

	@Then("^I should be navigated to (.*) screen$")
	public void i_should_be_navigated_to_screen(String pageTitle) {
		Assert.assertEquals("Page heading is Not correct", pageTitle.toUpperCase(), myAccountPage.getPageHeading());
		log.info("Page Title is: "+driver.getTitle());
		Assert.assertTrue("Page Title is Not correct", driver.getTitle().contains(pageTitle));
	    log.info("User is in My Accounts screen");
	}
	
	
}
