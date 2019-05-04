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
	WebElement searchBox;

	@FindBy(name = "submit_search")
	WebElement searchButton;

	@FindBy(xpath = "//div[@class='right-block']/h5/a[@class='product-name']")
	List <WebElement> productList;

	@FindBy(xpath = "//span[@class='cat-name']")
	WebElement subCategory;

	@FindBy(partialLinkText = "Sign in")
	WebElement signIn_Link;
	
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
		System.out.println("Number of Product Listed: "+productCount);
		String product = null;
		for(int i=0; i<productCount; i++) {
			product = productList.get(i).getText().toLowerCase();
			System.out.println(product);
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

	public void validateSubCategory(String subCategory) {
		subCategory = subCategory.toUpperCase().trim();
		String actualSubCategory = this.subCategory.getText().trim();
		System.out.println("Sub-Category Expected: "+subCategory+" Actual: "+actualSubCategory);
		Assert.assertEquals("Sub Category was not matching", subCategory,actualSubCategory);
	}

	public void validateTitle(String subCategory) {
		String actualTitle = driver.getTitle();
		System.out.println("Title Expected: "+subCategory+" Actual: "+actualTitle);
		Assert.assertTrue("Title was not matching", actualTitle.contains(subCategory));
	}

	public void clickOnSignInLink() {
		signIn_Link.click();
	}
}
