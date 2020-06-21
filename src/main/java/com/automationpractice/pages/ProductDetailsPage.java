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
	//Added to your wishlist.
	@FindBy(css = ".fancybox-close")
	private WebElement closeDialogBox;
	
	private final String socialMediaXpath = "//p[contains(@class,'socialsharing_product')]//button[%s]";
	
	@FindBy(css = "p.socialsharing_product>button")
	private List<WebElement> socialMediaButton;
	
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
}
