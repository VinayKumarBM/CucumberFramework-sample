package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
}
