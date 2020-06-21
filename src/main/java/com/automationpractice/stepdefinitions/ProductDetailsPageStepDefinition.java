package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pages.LandingPage;
import com.automationpractice.pages.ProductDetailsPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class ProductDetailsPageStepDefinition {
	private WebDriver driver;
	private LandingPage plp;	
	private ProductDetailsPage pdp;
	
	public ProductDetailsPageStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		plp = new LandingPage(driver);
		pdp = new ProductDetailsPage(driver);
	}

	@When("I navigate to product details page of '(.*)'")
	public void iNavigateToProductDetailsPageOf(String product) {
		plp.selectProduct(product);
	}

	@When("I write a new review '(.*)', '(.*)'")
	public void iWriteANewReview(String title, String comment) {
	    pdp.clickWriteReviewLink();
	    pdp.writeReview(title, comment);
	    pdp.submitReview();
	}

	@Then("I should see success (.*) display box")
	public void iShouldSeeSuccessMessage(String message) {
	    assertEquals("Success message did not match", message, pdp.getSuccessMessage());
	}

	@Then("I verify social media links on PDP")
	public void iVerifySocialMediaLinks(DataTable dataTable) {
		Map<String, String> expected = dataTable.asMap(String.class, String.class);
		Map<String, String> actual =pdp.getSocialMediaLinks();
		String key;
		for(Map.Entry<String, String> entry: expected.entrySet()) {
			key = entry.getKey();
			assertTrue(key+" link was not found",actual.containsKey(key));
			assertEquals("Social Media link title did not match",expected.get(key),actual.get(key));
		}
	}
	
	@When("I send a mail to friend {string}, {string} about the {string}")
	public void iSendMailToFriend(String name, String email, String product) {
		pdp.clickSendToFriendLink();
		pdp.enterFriendDetails(name, email);
		assertEquals("Product did not match", product, pdp.getProductName());
		pdp.sendMailToFriend();		
	}
	
	@Then("I should see {string} on adding product to wishlist")
	public void iShouldSeeMessageOnAddingProductToWishlist(String message) {
		pdp.clickAddToWishlistLink();
		assertEquals("Wishlist message did not match",message, pdp.getAddToWishlistMessage());
	}
}
