package com.automationpractice.stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.pages.HomePage;
import com.framework.utilities.DriverManager;
import com.framework.utilities.GetConfig;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchProductStepDefinition {
	private static final Logger log = LoggerFactory.getLogger(SearchProductStepDefinition.class);
	private HomePage homePage;
	private WebDriver driver;
	
	public SearchProductStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		homePage = new HomePage(driver);
	}

	@Given("I am on the application")
	public void i_am_on_the_application() {
	   log.info("Navigating to the application");
	   driver.get(GetConfig.getConfigProperty("base.url"));
	}


	@When("^I search for items containing \"(.*)\"$")
	public void i_search_for_items_containing(String searchKey) {
		log.info("Searching for product: "+searchKey);
		homePage.productSearch(searchKey);
	}

	@Then("^I should only see items related to \"(.*)\"$")
	public void i_should_only_see_items_related_to(String searchKey) {
		homePage.productMatches(searchKey);
		log.info("Searched product "+searchKey+" is displayed");
	}
	
	@When("^I have searched for (.*) category$")
	public void i_have_searched_for_category(String category) {
		homePage.selectCategory(category);
	}

	@When("^I filter results by (.*)$")
	public void i_filter_results(String subCategory) {
	  homePage.selectSubCategory(subCategory);
	}

	@Then("^I should see only (.*)$")
	public void i_should_only_see(String subCategory) {
	  Assert.assertEquals("Sub Category was not matching", subCategory.toUpperCase(),homePage.validateSubCategory());
	}

	@Then("^Title of the page should be (.*)$")
	public void title_of_the_page_should_be(String subCategory) {
		Assert.assertTrue("Title was not matching", driver.getTitle().contains(subCategory));
	}
}
