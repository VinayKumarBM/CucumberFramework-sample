package com.automationpractice.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pages.CheckoutPage;
import com.automationpractice.pages.LandingPage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.ProductDetailsPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckoutProductStepDefinition {
	private WebDriver driver;
	private LandingPage landingPage;	
	private LoginPage loginPage;	
	private ProductDetailsPage productDetailsPage;	
	private CheckoutPage checkoutPage;

	public CheckoutProductStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		checkoutPage = new CheckoutPage(driver);		
	}

	@When("I login into application with (.*), (.*)")
	public void iLoginIntoApplicationWithTestYahooComTest(String userName, String password) {
		loginPage.enterEmailToSignin(userName);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
	}

	@When("I add the \"(.*)\" to cart")
	public void iAddTheProductToCart(String product) {
		landingPage.selectProduct(product);
		productDetailsPage.addToCart();
		productDetailsPage.proceedToCheckout();
	}

	@When("I verify that \"(.*)\" is added to cart")
	public void iVerifyThatProductIsAddedToCart(String product) {
		assertEquals("Product in cart did not match", product, checkoutPage.getProductInCart());
		checkoutPage.proceedToCheckoutFromSummary();
	}

	@When("I use the delivery address as the billing address")
	public void iUseTheDeliveryAddressAsTheBillingAddress() {
		assertTrue("Delivery Address is not same as Billing Address", checkoutPage.isDeliverAddressSameAsBillingAddress());
		checkoutPage.proceedToCheckout();
	}

	@When("I agree to the terms of service")
	public void iAgreeToTheTermsOfService() {
		checkoutPage.agreeToTermsAndService();
		checkoutPage.proceedToCheckout();
	}

	@When("I confirm oredr by paying through check")
	public void iConfirmOredrByPayingThroughCheck() {
		checkoutPage.payByCheck();
		checkoutPage.confirmOrder();
	}

	@Then("I verify that order is placed sucessfully (.*)")
	public void iVerifyThatOrderIsPlacedSucessfully(String msg) {
		assertEquals("Order Confirmation was not displayed", msg, checkoutPage.getSuccessMessage());
	}
	
}
