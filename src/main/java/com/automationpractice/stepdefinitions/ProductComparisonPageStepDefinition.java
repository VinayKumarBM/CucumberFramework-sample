package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import com.automationpractice.pages.ComparePage;
import com.automationpractice.pages.LandingPage;
import com.automationpractice.pages.ProductDetailsPage;
import com.framework.utilities.DriverManager;
import com.framework.utilities.TestScenario;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductComparisonPageStepDefinition {
	private WebDriver driver;
	private ComparePage comparePage;	
	private LandingPage plp;
	private ProductDetailsPage pdp;
	
	public ProductComparisonPageStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		comparePage = new ComparePage(driver);
		pdp = new ProductDetailsPage(driver);
		plp = new LandingPage(driver);
	}
	
	@Then("I verify that there are {int} products in product comparision")
	public void iVerifyThatThereAreProductsInProductComparision(int count) {
		assertEquals("Product to compare did not match", count, comparePage.getProductsToCompare());
		assertEquals("Product to compare did not match", 3, comparePage.getFeaturesCompared());
	}

	@Then("I remove all products from product comparision")
	public void iRemoveAllProductsFromProductComparision() {
	    comparePage.deleteComparisonProducts();
	}
	
	@When("I {string} the product with {string} price")
	public void iViewTheProductWithPrice(String operation, String cost) {
	    comparePage.compareAndViewORAddProduct(cost, operation);
	}

	@Then("I should see the product details page of the selected product")
	public void iShouldSeeTheProductDetailsPageOfTheSelectedProduct() {
		assertEquals("Product Name did not match:", TestScenario.getSession().getVariable("productName"), pdp.getNameOfProduct());
		assertEquals("Product price did not match:", (Float)TestScenario.getSession().getVariable("productPrice"), 
				pdp.getProductPrice(), 0.00);
	}
	
	@Then("I should see display box with message {string} on adding {int} products")
	public void iShouldSeeDisplayBoxWithMessageOnAddingProducts(String message, Integer count) {
		plp.addProductsToCompare(count);
	    assertEquals("Waring message did not match", message, plp.getMessage());
	}
	
	@Then("I should see that correct product is added to cart to chceckout")
	public void iShouldSeeThatCorrectProductIsAddedToCart() {
		pdp.getProductAddedSuccessMessage();
		assertEquals("Product Name did not match:", TestScenario.getSession().getVariable("productName"), pdp.getNameOfProductInCart());
		assertEquals("Product price did not match:", (Float)TestScenario.getSession().getVariable("productPrice"), 
				pdp.getProductPriceInCart(), 0.00);
		pdp.proceedToCheckout();
	}
	
}