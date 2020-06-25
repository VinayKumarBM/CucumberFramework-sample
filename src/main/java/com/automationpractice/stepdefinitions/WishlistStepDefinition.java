package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.pages.MyAccountPage;
import com.automationpractice.pages.MyWishlistPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WishlistStepDefinition {
	private WebDriver driver;
	private MyWishlistPage wishlistPage;
	private MyAccountPage myAccountPage;
	private ElementOperations eo;
	
	public WishlistStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		wishlistPage = new MyWishlistPage(driver);
		myAccountPage = new MyAccountPage(driver);
		eo = new ElementOperations(driver);
	}

	@When("I navigate to {string} screen")
	public void iNavigateToScreen(String page) {
	    myAccountPage.navigateToMyAccount();
	    myAccountPage.navigateToPageInMyAccount(page);
	}

	@When("I remove all wishlists")
	public void iRemoveAllWishlists() {
	   wishlistPage.deleteAllWishlist();
	   assertTrue("All wishlists are not deleted", !wishlistPage.isWishlistPresent());
	}

	@When("I create a Wishlist {string}")
	public void iCreateAWishlist(String wishlistName) {
	   wishlistPage.createWishlist(wishlistName);
	}

	@When("I verify that {int} product is added to wishlist")
	public void iVerifyThatProductIsAddedToWishlist(int count) {
	   assertEquals("Wishlist product count did not match", count, wishlistPage.getWishlistProductCount());
	}
	
	@When("I view wishlist {string}")
	public void iViewWishlist(String wishlistName) {
	    wishlistPage.viewWishlist(wishlistName);
	}	

	@Then("I remove product {string} from wishlist")
	public void iRemoveProductFromWishlist(String product) {
		wishlistPage.deleteProductInWishlist(product);
		eo.refreshPage();
		assertEquals("Wishlist product count did not match", 0, wishlistPage.getWishlistProductCount());
	}
	
	@When("I return back to Home page")
	public void backToHomePage() {
	   myAccountPage.clickHomeButton();
	}
	
	@When("I return back to My Account page")
	public void backToMyAccount() {
		myAccountPage.clickBackToYourAccountButton();
	}
}
