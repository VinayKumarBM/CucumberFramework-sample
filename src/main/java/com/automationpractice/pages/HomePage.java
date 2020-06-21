package com.automationpractice.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.helper.URLUtility;

public class HomePage {
	private static final Logger log = LoggerFactory.getLogger(HomePage.class);
	private WebDriver driver;
	private ElementOperations eo;

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
	
	@FindBy(xpath = "//h4[text()='Categories']/following-sibling::div/div/ul/li/a")
	private List <WebElement> categoriesFooterLinks;
	
	@FindBy(xpath = "//h4[text()='Information']/following-sibling::ul/li/a")
	private List <WebElement> informationFooterLinks;
	
	@FindBy(xpath = "//h4/a[text()='My account']/../following-sibling::div/ul/li/a")
	private List <WebElement> myAccountFooterLinks;
	
	@FindBy(xpath = "//section[@id='social_block']//a")
	private List <WebElement> socialMediaLinks;
	
	@FindBy(xpath = "//section[@id='social_block']//a/span")
	private List <WebElement> socialMediaLinkText;
	
	@FindBy(id = "newsletter-input")
	private WebElement newsLetterTextbox;
	
	@FindBy(name = "submitNewsletter")
	private WebElement submitNewsLetterButton;
	
	@FindBy(css = "p.alert")
	private WebElement alertMessageText;
	
	@FindBy(css = "#block_contact_infos ul")
	private WebElement storeInformationText;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
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
	
	public String getAlertMessage() {
		eo.waitForVisibilityOfElement(alertMessageText);
		String msg = alertMessageText.getText().trim();
		log.info("Message: "+msg);
		return msg;
	}
	
	public void scrollToFooter() {
		eo.scrollToElement(newsLetterTextbox);
	}
	
	public void subscribeToNewsLetter(String email) {
		scrollToFooter();
		newsLetterTextbox.click();
		newsLetterTextbox.sendKeys(email);
		submitNewsLetterButton.click();
		log.info("Subscribed "+email+" for NEWS Letter");
	}

	public boolean isCategoriesFooterLinkBroken() {
		return validateLinks(categoriesFooterLinks);
	}
	
	public boolean isInformationFooterLinkBroken() {
		return validateLinks(informationFooterLinks);
	}
	
	public boolean isMyAccountFooterLinkBroken() {
		return validateLinks(myAccountFooterLinks);
	}
	
	public boolean isSocialMediaLinkBroken() {
		return validateLinks(socialMediaLinks);
	}
	
	public Map<String,String> getCategoriesFooterToolTipMap() {
		return getFooterMap(categoriesFooterLinks);
	}
	
	public Map<String,String> getMyAccountFooterToolTipMap() {
		return getFooterMap(myAccountFooterLinks);
	}
	
	public Map<String,String> getInformationFooterToolTipMap() {
		return getFooterMap(informationFooterLinks);
	}
	
	public List<String> getSocialMediaList() {
		List<String> socialMediaList = new ArrayList<String>();
		for(WebElement ele: socialMediaLinkText) {
			log.info(ele.getAttribute("innerHTML"));
			socialMediaList.add(ele.getAttribute("innerHTML").trim());
		}
		return socialMediaList;
	}
	
	private boolean validateLinks(List<WebElement> linkList) {
		for (WebElement ele : linkList) {
			if(!URLUtility.verifyLink(ele.getAttribute("href")))
				return false;
		}
		return true;
	}
	
	private Map<String,String> getFooterMap(List<WebElement> linkList) {
		Map<String,String> footerMap = new HashMap<String,String>();
		for (WebElement ele : linkList) {
			String linkName = ele.getText();
			String toolTip = ele.getAttribute("title");
			log.info(linkName +" -> "+ toolTip);
			footerMap.put(linkName, toolTip);
		}
		return footerMap;
	}
	
	public String getStoreInformation() {
		log.info(storeInformationText.getText().trim());
		return storeInformationText.getText().trim();
	}
	
	public boolean isSignInLinkDisplayed() {
		return eo.isVisible(signInLink);
	}
}

