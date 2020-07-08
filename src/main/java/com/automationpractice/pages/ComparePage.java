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
import com.automationpractice.helper.StringUtility;
import com.framework.utilities.TestScenario;

public class ComparePage {
	private static final Logger log = LoggerFactory.getLogger(ComparePage.class);
	private WebDriver driver;
	private ElementOperations eo;

	@FindBy(css = "a.product-name")
	private List <WebElement> productList;

	@FindBy(css = "td.feature-name")
	private List <WebElement> featureComparisionList;

	@FindBy(css = "a[title='Remove']")
	private List<WebElement> removeButton;

	@FindBy(css = "a[title='Add to cart']")
	private List<WebElement> addToCartButton;

	@FindBy(css = "a[title='View']")
	private List<WebElement> viewButton;

	@FindBy(css = ".product-price")
	private List<WebElement> priceText;	

	private final String removeXpath = "(//a[@title='Remove'])[%s]";

	public ComparePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	public int getProductsToCompare() {
		log.info("Products compared:");
		for (WebElement webElement : productList) {
			log.info(webElement.getText());
		}
		return productList.size();
	}

	public int getFeaturesCompared() {
		log.info("Compared featured");
		for (WebElement webElement : featureComparisionList) {
			log.info(webElement.getText());
		}
		return featureComparisionList.size();
	}

	public void deleteComparisonProducts() {
		for(int i=removeButton.size(); i > 0; i--) {
			driver.findElement(By.xpath(String.format(removeXpath, i))).click();
			eo.pause(2);
		}
		log.info("Removed all products");
	}

	public void compareAndViewORAddProduct(String cost, String operation) {
		float prodPrice1 = StringUtility.getPriceFromString(priceText.get(0).getText().trim());
		float prodPrice2 = StringUtility.getPriceFromString(priceText.get(1).getText().trim());
		String productName;
		float price;
		WebElement viewProductButton, addButton;
		if(prodPrice1 < prodPrice2)  {
			if(cost.equalsIgnoreCase("MIN")) {
				productName = productList.get(0).getText().trim();
				price = prodPrice1;
				viewProductButton = viewButton.get(0);
				addButton = addToCartButton.get(0);
			} else {
				productName = productList.get(1).getText().trim();
				price = prodPrice2;
				viewProductButton = viewButton.get(1);
				addButton = addToCartButton.get(1);
			}
		} else {
			if(!cost.equalsIgnoreCase("MIN")) {
				productName = productList.get(0).getText().trim();
				price = prodPrice1;
				addButton = addToCartButton.get(0);
				viewProductButton = viewButton.get(0);
			} else {
				productName = productList.get(1).getText().trim();
				price = prodPrice2;
				addButton = addToCartButton.get(1);
				viewProductButton = viewButton.get(1);
			}
		}
		TestScenario.getSession().setVariable("productName", productName);
		TestScenario.getSession().setVariable("productPrice", price);
		if(operation.equalsIgnoreCase("VIEW")) {
			viewProductButton.click();
		} else {
			addButton.click();
		}
	}

}
