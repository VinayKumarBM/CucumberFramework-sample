package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.automationpractice.helper.JsonReader;
import com.automationpractice.helper.StringUtility;
import com.automationpractice.models.Address;
import com.automationpractice.pages.AddressPage;
import com.automationpractice.pages.CheckoutPage;
import com.automationpractice.pages.LandingPage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.ProductDetailsPage;
import com.framework.utilities.DriverManager;
import com.framework.utilities.TestScenario;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckoutProductStepDefinition {
	private WebDriver driver;
	private LandingPage landingPage;	
	private LoginPage loginPage;	
	private ProductDetailsPage productDetailsPage;	
	private CheckoutPage checkoutPage;
	private AddressPage addressPage;
	private JsonReader json = new JsonReader();
	private Address address;

	public CheckoutProductStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		checkoutPage = new CheckoutPage(driver);	
		addressPage = new AddressPage(driver);
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
		checkoutPage.useDeliverAddressAsBillingAddress(true);
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
	
	@When("I verify that product is in cart")
	public void iVerifyThatProductIsAddedToCart() {
		assertNotNull("Product is not in cart", checkoutPage.getProductInCart());
		checkoutPage.proceedToCheckoutFromSummary();
	}
	
	@When("I confirm oredr by paying through bank wire")
	public void iConfirmOredrByPayingThroughBankWire() {
		checkoutPage.payByBankWire();
		checkoutPage.confirmOrder();
	}
	
	@Then("I verify that order is placed through bank wire sucessfully {string}")
	public void iVerifyThatOrderIsPlacedThroughBankWireSucessfully(String msg) {
		assertEquals("Order Confirmation was not displayed", msg, checkoutPage.getWirePaymentSuccessMessage());
	}
	
	@When("I add a comment about my order {string}")
	public void iAddACommentAboutMyOrder(String comment) {
	    checkoutPage.addComment(comment);
	}

	@When("I add new address {string} to use as the billing address")
	public void iAddNewAddressToUseAsTheBillingAddress(String addressReference) {
		checkoutPage.addNewAddress();
		address = json.getAddressByReference(addressReference);
		addressPage.addNewAddress(address);
		String billingAddress = addressReference+StringUtility.getAlphaNumericString(5);
		addressPage.enterReferenceAddress(billingAddress);
		addressPage.clickSaveButton();
		checkoutPage.useDeliverAddressAsBillingAddress(false);
		checkoutPage.selectBillingAddress(billingAddress);
	}
	
	@Then("I proceed to shipping details of order")
	public void iProceedToShippingDetailsOfOrder() {
		checkoutPage.proceedToCheckout();
	}
	
	@Then("I will go back to Order History screen")
	public void iWillGoBackToOrderHistoryScreen() {
		checkoutPage.clickBackToOrderLink();
	}
	
	@Then("I validate the details like color {string} size {string} of product {string} in checkout page")
	public void iValidateTheDetailsLikeColorSizeOfProductInCheckoutPage(String color, String size, String product) {
		assertEquals("Product Name did not match: ", product, checkoutPage.getProductInCart());
		assertEquals("Product attributes did not match: ", "Color : "+color+", Size : "+size, checkoutPage.getProductAttributes());
		assertEquals("Product Quantity did not match: ", String.valueOf(TestScenario.getSession().getVariable("quantity")), 
				checkoutPage.getQuantity());		
		assertEquals("Product Total did not match: ", (Float)TestScenario.getSession().getVariable("productPrice"), 
				checkoutPage.getProductTotalPrice(), 0.00);
	}
	
	@Then("I remove product from checkout page")
	public void iRemoveProductInCheckoutPage() {
		checkoutPage.removeProduct();
	}
}
