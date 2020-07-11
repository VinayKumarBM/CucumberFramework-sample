package com.automationpractice.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pages.LandingPage;
import com.automationpractice.pages.ProductDetailsPage;
import com.framework.utilities.DriverManager;
import com.framework.utilities.TestScenario;

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

	@Then("I verify social media links available")
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
	
	@When("I change the product view to {string}")
	public void iChangeTheProductViewTo(String view) {
		plp.changeView(view);
	}
	
	@When("I add {int} product to compare")
	public void iAddProductToCompare(int count) {
		assertFalse("Compare button is enabled ",plp.isCompareButtonEnabled());
		plp.addProductsToCompare(count);
		assertTrue("Compare button is disabled ",plp.isCompareButtonEnabled());
		assertEquals("Product count to compare did not match", count, plp.getCompareProductCount());		
	}
	
	@Then("I compare added products")
	public void iCompareAddedProducts() {
		plp.clickCompareButton();
	}
	
	@Then("I remove {int} from {int} products that were added")
	public void iRemoveFromProductsThatWereAdded(Integer removeCount, Integer addCount) {
		plp.removeProductsFromCompare(removeCount);
		assertEquals("Product count to compare did not match", addCount-removeCount, plp.getCompareProductCount());	
	}
	
	@When("I enter the product quantity {string}")
	public void iEnterTheProductQuantity(String quantity) {		
	    pdp.enterQuantity(quantity);
	    assertEquals("Quanity did not match", quantity, String.valueOf(pdp.getQuantity()));
	}

	@When("I decrease the product quantity by {int}")
	public void iDecreaseTheProductQuantityBy(int decrease) {
	    int expectedQuantity = pdp.getQuantity() - decrease;
	    for (int i = 0; i < decrease; i++) {
			pdp.decrementQuantityByOne();
		}
	    assertEquals("Quanity did not match", expectedQuantity, pdp.getQuantity());
	}

	@When("I increase the product quantity by {int}")
	public void iIncreaseTheProductQuantityBy(Integer increase) {
	    int expectedQuantity = pdp.getQuantity() + increase;
	    for (int i = 0; i < increase; i++) {
			pdp.incrementQuantityByOne();
		}
	    assertEquals("Quanity did not match", expectedQuantity, pdp.getQuantity());
	}

	@When("I select size as {string} and color as {string}")
	public void iSelectSizeAsAndColorAs(String size, String color) {
	    pdp.selectColor(color);
	    pdp.selectSize(size);
	}

	@Then("I validate the details like color {string} size {string}after adding product {string} to cart")
	public void iValidateTheDetailsLikeColorSizeAfterAddingProductToCart(String color, String size, String product) {
		int expectedQuantity = pdp.getQuantity();
		float total = expectedQuantity * pdp.getProductPrice();
		pdp.addToCart();
		assertEquals("Product Quantity did not match: ", String.valueOf(expectedQuantity), pdp.getNumberOfQuantityText());
		assertEquals("Product Name did not match: ", product, pdp.getNameOfProductInCart());
		assertEquals("Product attributes did not match: ", color+", "+size, pdp.getProductAttributes());
		assertEquals("Product Quantity did not match: ", String.valueOf(expectedQuantity), pdp.getQuantityText());		
		assertEquals("Product Total did not match: ", total, pdp.getProductPriceInCart(), 0.00);
		TestScenario.getSession().setVariable("productPrice", total);
		TestScenario.getSession().setVariable("quantity", expectedQuantity);
	}
	
	@Then("I checout product in cart")
	public void iChecoutProductInCart() {
	    pdp.proceedToCheckout();
	}	
}
