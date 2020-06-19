package com.automationpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandingPage {
	private static final Logger log = LoggerFactory.getLogger(LandingPage.class);
	private WebDriver driver;
	private final String productXpath = "//h5[@itemprop='name']/a[contains(text(),'%s')]";
	
	@FindBy(xpath = "//h5[@itemprop='name']/a[@class='product-name']")
	private List <WebElement> productList;
	
	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void selectProduct(String product) {
		driver.findElement(By.xpath(String.format(productXpath, product))).click();
		log.info(product+" selected in PLP");
	}
		
}
