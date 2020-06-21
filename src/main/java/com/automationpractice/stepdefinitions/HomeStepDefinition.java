package com.automationpractice.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

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
import io.cucumber.datatable.DataTable;

public class HomeStepDefinition {
	private static final Logger log = LoggerFactory.getLogger(HomeStepDefinition.class);
	private HomePage homePage;
	private WebDriver driver;
	
	public HomeStepDefinition() {
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
	
	@When("I enter (.*) for news letter subscription")
	public void iEnterEmailForNewsLetterSubscription(String email) {
		homePage.subscribeToNewsLetter(String.format(email, System.currentTimeMillis()));
	}
	
	@Then("I verify that message '(.*)' is displayed")
	public void iVerifyThatMessageIsDisplayed(String msg) {
		assertEquals("Success message was not displayed ", msg, homePage.getAlertMessage());
	}
	
	@When("I navigate to footer section")
	public void iNavigateToFooterSection() {
	    homePage.scrollToFooter();
	}

	@Then("I verify Categories footer links are not broken")
	public void iVerifyCategoriesFooterLinksAreNotBroken(DataTable dataTable) {
		assertTrue("Categories footer links are broken", homePage.isCategoriesFooterLinkBroken());
		Map<String,String> dataMap = dataTable.asMap(String.class, String.class);
		Map<String,String> toolTipMap = homePage.getCategoriesFooterToolTipMap();
		for (Map.Entry<String, String> entry: dataMap.entrySet()) { 
			String key = entry.getKey();
			assertTrue(key+" link was not found",toolTipMap.containsKey(key));
			assertTrue("Tool Tip did not match", toolTipMap.get(key).contains(entry.getValue()));
		}
	}
	
	@Then("I verify Information footer links are not broken")
	public void iVerifyInformationFooterLinksAreNotBroken(DataTable dataTable) {
		assertTrue("Information footer links are broken", homePage.isInformationFooterLinkBroken());
		Map<String,String> dataMap = dataTable.asMap(String.class, String.class);
		Map<String,String> toolTipMap = homePage.getInformationFooterToolTipMap();
		for (Map.Entry<String, String> entry: dataMap.entrySet()) { 
			String key = entry.getKey();
			assertTrue(key+" link was not found",toolTipMap.containsKey(key));
			assertEquals("Tool Tip did not match", entry.getValue(), toolTipMap.get(key));
		}
	}	
	
	@Then("I verify My account footer links are not broken")
	public void iVerifyMyAccountFooterLinksAreNotBroken(DataTable dataTable) {
		assertTrue("My account footer links are broken", homePage.isMyAccountFooterLinkBroken());
		Map<String,String> dataMap = dataTable.asMap(String.class, String.class);
		Map<String,String> toolTipMap = homePage.getMyAccountFooterToolTipMap();
		for (Map.Entry<String, String> entry: dataMap.entrySet()) { 
			String key = entry.getKey();
			assertTrue(key+" link was not found",toolTipMap.containsKey(key));
			assertEquals("Tool Tip did not match", entry.getValue(), toolTipMap.get(key));
		}
	}
	
	@Then("I verify Social Media links in footer")
	public void iVerifySocialMediaLinks(DataTable dataTable) {
		assertTrue("Social Media links are broken", homePage.isSocialMediaLinkBroken());
		List<String> expectedList = dataTable.asList();
		List<String> actualList = homePage.getSocialMediaList();
		for(int i=0; i<expectedList.size(); i++) {
			assertEquals("Social Media Link did not match", expectedList.get(i), actualList.get(i));
		}
	} 
	
	@Then("I verify the contact details in Store information section of footer")
	public void iVerifyStoreInformationSection(DataTable dataTable) {
		Map<Object, Object> map = (dataTable.asMaps(String.class, String.class)).get(0);
		String info = homePage.getStoreInformation();
		assertTrue("Email is not correct", info.contains((String) map.get("email")));
		assertTrue("Email is not correct", info.contains((String) map.get("contact")));
		assertTrue("Email is not correct", info.contains((String) map.get("address")));
	}
}
