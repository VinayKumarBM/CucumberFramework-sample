package com.automationpractice.pages;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
	private static final Logger log = LoggerFactory.getLogger(HomePage.class);
	private WebDriver driver;

	@FindBy(name = "search_query")
	private WebElement searchBox;

	@FindBy(name = "submit_search")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@class='right-block']/h5/a[@class='product-name']")
	private List <WebElement> productList;

	@FindBy(xpath = "//span[@class='cat-name']")
	private WebElement subCategory;

	@FindBy(partialLinkText = "Sign in")
	private WebElement signInLink;
	
	@FindBy(css = "#contact-link>a")
	private WebElement contactUsLink;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void productSearch(String searchKey) {
		searchBox.clear();
		searchBox.sendKeys(searchKey);
		searchButton.click();
	}

	public void productMatches(String searchKey) {
		int productCount = productList.size();
		log.info("Number of Product Listed: "+productCount);
		String product = null;
		for(int i=0; i<productCount; i++) {
			product = productList.get(i).getText().toLowerCase();
			log.info(product);
			Assert.assertTrue(product+" does not contain search key "+searchKey, product.contains(searchKey));
		}
	}

	public void selectCategory(String category) {
		log.info("Selecting Category "+category);
		driver.findElement(By.linkText(category.toUpperCase())).click();
	}

	public void selectSubCategory(String subCategory) {
		log.info("Selecting Sub-Category "+subCategory);
		driver.findElement(By.linkText(subCategory.toUpperCase())).click();
	}

	public String validateSubCategory() {
		String actualSubCategory = this.subCategory.getText().trim();
		log.info("Sub-Category Actual: "+actualSubCategory);
		return actualSubCategory;
	}

	public void clickOnSignInLink() {
		signInLink.click();
	}
	
	public void clickOnContactUsLink() {
		contactUsLink.click();
	}
}
