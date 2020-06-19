package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;

public class CheckoutPage {
	private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);
	private WebDriver driver;
	private ElementOperations eo;
	
	@FindBy(css = "td>p.product-name>a")
	private WebElement productName;
	
	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	private WebElement proceedToCheckoutSummary;
	
	@FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout;
	
	@FindBy(id = "addressesAreEquals")
	private WebElement useBillingAddress;
	
	@FindBy(id = "cgv")
	private WebElement agreeToTnC;
	
	@FindBy(css = "a[title='Pay by check.']")
	private WebElement payByCheckButton;
	
	@FindBy(xpath = "//span[text()='I confirm my order']")
	private WebElement confirmOrderButton;
	
	@FindBy(css = "p.alert-success")
	private WebElement successText;
	
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	public String getProductInCart() {
		eo.waitForVisibilityOfElement(productName);
		String product = productName.getText();
		log.info("Product in cart: "+product);
		return product;
	}
	
	public void proceedToCheckoutFromSummary() {
		eo.waitForElementToBeClickable(proceedToCheckoutSummary);
		proceedToCheckoutSummary.click();
		log.info("Clicked on Proceed To Checkout Button");
	}
	
	public void proceedToCheckout() {
		eo.waitForElementToBeClickable(proceedToCheckout);
		proceedToCheckout.click();
		log.info("Clicked on Proceed To Checkout Button");
	}
	
	public boolean isDeliverAddressSameAsBillingAddress() {
		return useBillingAddress.isSelected();
	}
	
	public void agreeToTermsAndService() {
		agreeToTnC.click();
		log.info("Agreed to T&C");
	}
	
	public void payByCheck() {
		eo.waitForElementToBeClickable(payByCheckButton);
		payByCheckButton.click();
		log.info("Clicked on Pay By Check Button");
	}
	
	public void confirmOrder() {
		eo.waitForElementToBeClickable(confirmOrderButton);
		confirmOrderButton.click();
		log.info("Clicked on Confirm Order Button");
	}
	
	public String getSuccessMessage() {
		eo.waitForVisibilityOfElement(successText);
		String msg = successText.getText();
		log.info("Message: "+msg);
		return msg;
	}
}
