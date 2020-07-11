package com.automationpractice.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.helper.StringUtility;

public class ProductDetailsPage {
	private static final Logger log = LoggerFactory.getLogger(ProductDetailsPage.class);
	private WebDriver driver;
	private ElementOperations eo;

	@FindBy(xpath = "//span[text()='Add to cart']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout;

	@FindBy(css = ".open-comment-form")
	private WebElement writeReview;

	@FindBy(id = "comment_title")
	private WebElement reviewTitleInputbox;

	@FindBy(id = "content")
	private WebElement reviewContentInputbox;

	@FindBy(id = "submitNewMessage")
	private WebElement submitReviewButton;

	@FindBy(css = "div.fancybox-inner>p")
	private WebElement successMessageBox;

	@FindBy(css = "div.fancybox-inner>p.submit>.button")
	private WebElement okButton;

	@FindBy(css = "li.sendtofriend>a")
	private WebElement sendToFriendLink;

	@FindBy(id = "friend_name")
	private WebElement friendNameInputbox;

	@FindBy(id = "friend_email")
	private WebElement friendEmailInputbox;

	@FindBy(id = "sendEmail")
	private WebElement sendEmailButton;

	@FindBy(css = "div.fancybox-inner p.product_name")
	private WebElement productNameText;

	@FindBy(id = "wishlist_button")
	private WebElement addToWishlistLink;

	@FindBy(css = ".fancybox-error")
	private WebElement addToWishlistMessage;

	@FindBy(css = ".fancybox-close")
	private WebElement closeDialogBox;

	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement productText;

	@FindBy(id = "our_price_display")
	private WebElement productPrice;

	@FindBy(css = "div#layer_cart h2")
	private WebElement productAddedSuccessText;

	@FindBy(css = ".layer_cart_product_info .product-name")
	private WebElement productInCartNameText;

	@FindBy(id = "layer_cart_product_price")
	private WebElement productInCartPriceText;		

	@FindBy(css = "p.socialsharing_product>button")
	private List<WebElement> socialMediaButton;

	@FindBy(id = "quantity_wanted")
	private WebElement quantityInputbox;

	@FindBy(css = ".button-minus>span")
	private WebElement minusButton;

	@FindBy(css = ".button-plus>span")
	private WebElement plusButton;

	@FindBy(id = "group_1")
	private WebElement sizeDropdown;
	
	@FindBy(id = "layer_cart_product_attributes")
	private WebElement productAttributesText;
	
	@FindBy(id = "layer_cart_product_quantity")
	private WebElement quantityText;
	
	@FindBy(css = "span>span.ajax_cart_quantity")
	private WebElement itemInCartText;

	private final String colorListXpath = "//ul[@id='color_to_pick_list']/li/a[@name='%s']";
	private final String socialMediaXpath = "//p[contains(@class,'socialsharing_product')]//button[%s]";

	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	public void addToCart() {
		addToCartButton.click();
		log.info("Clicked on add to cart Button");
	}

	public void proceedToCheckout() {
		eo.waitForElementToBeClickable(proceedToCheckout);
		proceedToCheckout.click();
		log.info("Clicked on Proceed To Checkout Button");
	}	

	public void clickWriteReviewLink() {
		writeReview.click();
		log.info("Clicked write review link");
	}
	public void writeReview(String title, String comment) {
		reviewTitleInputbox.sendKeys(title);
		reviewContentInputbox.sendKeys(comment);
		log.info("Entered review comments");
	}

	public void submitReview() {
		submitReviewButton.click();
		log.info("Submitted review comments");
	}

	public String getSuccessMessage() {
		String msg = successMessageBox.getText();
		log.info("Message: "+msg);
		okButton.click();
		return msg;
	}

	public Map<String,String> getSocialMediaLinks() {
		Map<String,String> mediaMap = new HashMap<String, String>();
		String mainWindow = driver.getWindowHandle();
		String link;
		for (int i=1; i<=socialMediaButton.size(); i++) {
			WebElement ele = driver.findElement(By.xpath(String.format(socialMediaXpath, i)));
			link = ele.getText().trim();		
			ele.click();
			eo.switchToNewWindow();
			eo.pause(2);
			mediaMap.put(link, driver.getTitle().trim());
			driver.close();
			driver.switchTo().window(mainWindow);
			eo.pause(1);
		}
		return mediaMap;
	}

	public void clickSendToFriendLink() {
		sendToFriendLink.click();
		log.info("Clicked on Send To Friend link");
	}

	public String getProductName() {
		log.info("Product Name: "+productNameText.getText());
		return productNameText.getText().trim();
	}

	public void enterFriendDetails(String name, String email) {
		friendNameInputbox.sendKeys(name);
		friendEmailInputbox.sendKeys(email);
		log.info("Enter details of freind to send mail about product");
	}

	public void sendMailToFriend() {
		sendEmailButton.click();
		log.info("Clicked on send email button");
	}

	public void clickAddToWishlistLink() {
		addToWishlistLink.click();
		log.info("Clicked on Add To Wishlist link");
	}

	public String getAddToWishlistMessage() {
		String msg = addToWishlistMessage.getText();
		log.info("Message: "+msg);
		closeDialogBox.click();
		log.info("Closed the message box");
		return msg;
	}

	public String getNameOfProduct() {
		log.info("Product Name: "+productText.getText());
		return productText.getText().trim();
	}

	public float getProductPrice() {
		log.info("Product Price: "+productPrice.getText());
		return StringUtility.getPriceFromString(productPrice.getText().trim());
	}

	public String getProductAddedSuccessMessage() {
		eo.waitForVisibilityOfElement(productAddedSuccessText);
		log.info(productAddedSuccessText.getText());
		return productAddedSuccessText.getText();
	}

	public String getNameOfProductInCart() {
		log.info("Product Name: "+productInCartNameText.getText());
		return productInCartNameText.getText().trim();
	}

	public float getProductPriceInCart() {
		log.info("Product Price: "+productInCartPriceText.getText());
		return StringUtility.getPriceFromString(productInCartPriceText.getText().trim());
	}

	public int getQuantity() {
		log.info("Quantity: "+quantityInputbox.getAttribute("value"));
		return Integer.parseInt(quantityInputbox.getAttribute("value"));
	}
	
	public void enterQuantity(String quantity) {
		quantityInputbox.clear();
		quantityInputbox.sendKeys(quantity);
		log.info("Entered quantity "+quantity);
	}
	
	public void incrementQuantityByOne() {
		plusButton.click();
		eo.pause(1);
		log.info("Increased quantity by one");
	}

	public void decrementQuantityByOne() {
		minusButton.click();
		eo.pause(1);
		log.info("decreased quantity by one");
	}
	
	public void selectSize(String size) {
		eo.selectByVisibleText(sizeDropdown, size);
		log.info("Selected size "+size);
	}
	
	public void selectColor(String color) {
		driver.findElement(By.xpath(String.format(colorListXpath, color))).click();
		log.info("Selected color "+color);
	}
	
	public String getProductAttributes() {
		String productAttributes = productAttributesText.getText().trim();
		log.info("Product Details: "+productAttributes);
		return productAttributes;
	}
	
	public String getNumberOfQuantityText() {
		eo.waitForElementToBeClickable(proceedToCheckout);
		String itemInCart = itemInCartText.getText().trim();
		log.info("Item In Cart: "+itemInCart);
		return itemInCart;
	}
	
	public String getQuantityText() {
		String quantity = quantityText.getText().trim();
		log.info("Product Quantity: "+quantity);
		return quantity;
	}
}
