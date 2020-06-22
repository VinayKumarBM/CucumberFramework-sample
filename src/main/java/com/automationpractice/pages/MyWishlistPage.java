package com.automationpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;

public class MyWishlistPage {
	private static final Logger log = LoggerFactory.getLogger(MyWishlistPage.class);
	private WebDriver driver;
	private ElementOperations eo;
	
	@FindBy(id = "name")
	private WebElement wishlistNameInputbox;

	@FindBy(id = "submitWishlist")
	private WebElement submitWishlistButton;
	
	@FindBy(xpath = "//td[2]")
	private WebElement wishlistCountText;
	
	@FindBy(css = "p.product-name")
	private WebElement productNameText;
	
	@FindBy(css = ".wishlist_delete>a>i")
	private List<WebElement> deleteWishlistLink;
	
	private final String wishlistLinksXpath = "//a[contains(text(),'%s')]";
	private final String deleteWishlistProductXpath = "//p[contains(text(),'%s')]/../a[@class='lnkdel']";
	private final String deleteWishlistXpath = "//td[@class='wishlist_delete']/a/i[%s]";
	
	public MyWishlistPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}
	
	public void createWishlist(String wishlistName) {
		wishlistNameInputbox.sendKeys(wishlistName);
		submitWishlistButton.click();
		log.info("Created new Wishlist "+wishlistName);
	}
	
	public void viewWishlist(String wishlistName) {
		driver.findElement(By.xpath(String.format(wishlistLinksXpath, wishlistName))).click();	
		log.info("Clicked on "+wishlistName+" to view the content.");
	}

	public int getWishlistProductCount() {
		log.info("Wishlist product count "+wishlistCountText.getText());
		return Integer.parseInt(wishlistCountText.getText().trim());
	}
	
	public boolean isWishlistPresent() {
		boolean isPresent = eo.isElementPresent(By.xpath(String.format(deleteWishlistXpath, 1)));
		log.info("Wishlist is present: "+isPresent);
		return isPresent;
	}
	
	public void deleteProductInWishlist(String product) {
		driver.findElement(By.xpath(String.format(deleteWishlistProductXpath, product))).click();
		log.info("Removed "+product+" from Wishlist");
	}
	
	public void deleteAllWishlist() {		
		if(isWishlistPresent()) {
			for(int i=1; i<=deleteWishlistLink.size(); i++) {
				driver.findElement(By.xpath(String.format(deleteWishlistXpath, i))).click();
				eo.getAlertMessageAndAccept();
				eo.pause(5);
			}
			log.info("Deleted all the wishlists");
		} 
		else {
			log.info("There were no wishlist");
		}
	}	
}
