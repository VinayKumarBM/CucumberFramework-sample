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

public class LandingPage {
	private static final Logger log = LoggerFactory.getLogger(LandingPage.class);
	private WebDriver driver;
	private ElementOperations eo;
	
	private final String productXpath = "//h5[@itemprop='name']/a[contains(text(),'%s')]";
	private final String viewXpath = "//a[@title='%s']";
	
	@FindBy(xpath = "//h5[@itemprop='name']/a[@class='product-name']")
	private List <WebElement> productList;
	
	@FindBy(css = "div.compare a")
	private List <WebElement> addToCompareLink;
	
	@FindBy(css = "a.add_to_compare.checked")
	private List <WebElement> removeFromCompareLink;
	
	@FindBy(css = ".bt_compare")
	private WebElement compareButton;
	
	@FindBy(css = ".total-compare-val")
	private WebElement compareCountText;
	
	@FindBy(css = "div.fancybox-inner>p")
	private WebElement messageBox;
	
	@FindBy(css = "a[title='Close']")
	private WebElement closeButton;
	
	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	public void selectProduct(String product) {
		driver.findElement(By.xpath(String.format(productXpath, product))).click();
		log.info(product+" selected in PLP");
	}
	
	public void changeView(String view) {
		driver.findElement(By.xpath(String.format(viewXpath, view))).click();
		log.info("Changed the view to "+view);
	}
	
	public void addProductsToCompare(int count) {
		for (int i = 0; i < count; i++) {
			addToCompareLink.get(i).click();
			log.info("Added "+productList.get(i).getText()+" to compare");
			eo.pause(2);
		}
	}
	
	public boolean isCompareButtonEnabled() {
		eo.pause(2);
		log.info("Compare button is enabled: "+compareButton.isEnabled());
		return compareButton.isEnabled();
	}
	
	public int getCompareProductCount() {
		eo.pause(2);
		String count = compareCountText.getText();
		log.info("Products to compare: "+count);
		return Integer.parseInt(count);
	}
	
	public void clickCompareButton() {
		compareButton.click();
		log.info("Clicked on Compare button");
	}
	
	public String getMessage() {
		String msg = messageBox.getText();
		log.info("Message: "+msg);
		closeButton.click();
		return msg;
	}
	
	public void removeProductsFromCompare(int count) {
		for (int i = 0; i < count; i++) {
			removeFromCompareLink.get(i).click();
			eo.pause(2);
		}
	}
}
