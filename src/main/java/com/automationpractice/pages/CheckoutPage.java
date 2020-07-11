package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.helper.StringUtility;

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
	
	@FindBy(css = "a[title='Pay by bank wire']")
	private WebElement payByBankWireButton;
	
	@FindBy(xpath = "//span[text()='I confirm my order']")
	private WebElement confirmOrderButton;
	
	@FindBy(css = "p.alert-success")
	private WebElement successText;
	
	@FindBy(css = ".cheque-indent>strong")
	private WebElement wirePaymentSuccessText;

	@FindBy(id = "id_address_invoice")
	private WebElement billingAddressDropdown;
	
	@FindBy(id = "id_address_delivery")
	private WebElement deliveryAddressDropdown;
	
	@FindBy(css = "p.submit>a[title='Add']")
	private WebElement addNewAddressButton;
	
	@FindBy(name = "message")
	private WebElement messageTextarea;
	
	@FindBy(css = "p>a[title='Back to orders']")
	private WebElement backToOrdersLink;
	
	@FindBy(css = "td.cart_description>small>a")
	private WebElement productAttributeText;
	
	@FindBy(css = ".cart_total span,price")
	private WebElement totalText;
	
	@FindBy(css = ".cart_quantity_input")
	private WebElement quantityInputbox;
	
	@FindBy(css = ".cart_quantity_delete")
	private WebElement deleteButton;
	
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
	
	public void useDeliverAddressAsBillingAddress(boolean checked) {
		if((checked && !isDeliverAddressSameAsBillingAddress())||
				(!checked && isDeliverAddressSameAsBillingAddress())){
			useBillingAddress.click();
			log.info("Is billing address same as delivery address: "+checked);
			eo.pause(5);
		}
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
	
	public void payByBankWire() {
		eo.waitForElementToBeClickable(payByBankWireButton);
		payByBankWireButton.click();
		log.info("Clicked on Pay By Bank Wire Button");
	}
	
	public String getWirePaymentSuccessMessage() {
		eo.waitForVisibilityOfElement(wirePaymentSuccessText);
		String msg = wirePaymentSuccessText.getText();
		log.info("Message: "+msg);
		return msg;
	}
	
	public void addComment(String comment) {
		messageTextarea.sendKeys(comment);
		log.info("Added comment: "+comment);
	}
	
	public void addNewAddress() {
		addNewAddressButton.click();
		log.info("Clicked on Add New Address button");
	}
	
	public void selectBillingAddress(String billingAddress) {
		eo.selectByVisibleText(billingAddressDropdown, billingAddress);
		log.info("Selected new billing address "+billingAddress); 
	}
	
	public void clickBackToOrderLink() {
		backToOrdersLink.click();
		log.info("Clicked on Back To Order Link");
	}

	public String getProductAttributes() {
		String attribute = productAttributeText.getText().trim();
		log.info("Product Attribute: "+attribute);
		return attribute;
	}

	public String getQuantity() {
		String quantity = quantityInputbox.getAttribute("value");
		log.info("Product in Checkout page: "+quantity);
		return quantity;
	}

	public float getProductTotalPrice() {
		float total = StringUtility.getPriceFromString(totalText.getText());
		log.info("Product Total: "+total);
		return total;
	}
	
	public void removeProduct() {
		deleteButton.click();
		log.info("Clicked on Delete button");
	}
}
