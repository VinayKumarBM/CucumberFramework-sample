package com.automationpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.models.Address;

public class AddressPage {
	private static final Logger log = LoggerFactory.getLogger(AddressPage.class);
	private WebDriver driver;
	private ElementOperations eo;
	
	public AddressPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	@FindBy(xpath = "//a[@title='Add an address']")
	private WebElement addAddressButton;
	
	@FindBy(id = "company")
	private WebElement company;
	
	@FindBy(id = "firstname")
	private WebElement firstName;
	
	@FindBy(id = "lastname")
	private WebElement lastName;
	
	@FindBy(id = "address1")
	private WebElement addressLine1;
	
	@FindBy(id = "address2")
	private WebElement addressLine2;	
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(id = "id_state")
	private WebElement state;
	
	@FindBy(id = "postcode")
	private WebElement zipCode;
	
	@FindBy(id = "id_country")
	private WebElement country;
	
	@FindBy(id = "phone")
	private WebElement homePhone;
	
	@FindBy(id = "phone_mobile")
	private WebElement mobilePhone;
	
	@FindBy(id = "other")
	private WebElement additionalInformation;
	
	@FindBy(id = "alias")
	private WebElement referenceAddress;
	
	@FindBy(id = "submitAddress")
	private WebElement saveButton;
	
	@FindBy(css = "ul.footer_links>li>a.button")
	private WebElement backToAddressButton;
	
	@FindBy(xpath = "//a[@title='Delete']")
	private List<WebElement> deleteButton;
	
	@FindBy(css = ".alternate_item")
	private WebElement secondaryAddressText;
	
	@FindBy(xpath = "(//a[@title='Update'])[2]")
	private WebElement updateButton;
	
	private final String deleteAddressXpath = "(//a[@title='Delete'])[%s]";
	
	public void clickAddNewAddressButton() {
		addAddressButton.click();
		log.info("Clicked on Add New Address button");
	}
		
	public void enterFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	
	public void enterCompany(String company) {
		this.company.clear();
		this.company.sendKeys(company);
	}
	
	public void enterAddressLine1(String address1) {
		this.addressLine1.clear();
		this.addressLine1.sendKeys(address1);
	}
	
	public void enterAddressLine2(String address2) {
		this.addressLine2.clear();
		this.addressLine2.sendKeys(address2);
	}
	
	public void selectCountry(String country) {
		new Select(this.country).selectByVisibleText(country);
	}
	
	public void selectState(String state) {
		new Select(this.state).selectByVisibleText(state);
	}
	
	public void enterZipCode(String zipCode) {
		this.zipCode.clear();
		this.zipCode.sendKeys(zipCode);
	}
	
	public void enterCity(String city) {
		this.city.clear();
		this.city.sendKeys(city);
	}
	
	public void enterHomePhone(String homePhone) {
		this.homePhone.clear();
		this.homePhone.sendKeys(homePhone);
	}
	
	public void enterMobilePhone(String mobilePhone) {
		this.mobilePhone.clear();
		this.mobilePhone.sendKeys(mobilePhone);
	}
	
	public void enterAdditionalInformation(String additionalInformation) {
		this.additionalInformation.clear();
		this.additionalInformation.sendKeys(additionalInformation);
	}
	
	public void enterReferenceAddress(String referenceAddress) {
		this.referenceAddress.clear();
		this.referenceAddress.sendKeys(referenceAddress);
	}
	
	public void clickSaveButton() {
		saveButton.click();
		log.info("Clicked on Save button");
	}
	
	public void addNewAddress(Address address) {		
		enterFirstName(address.newAddress.firstName);
		enterLastName(address.newAddress.lastName);
		enterCompany(address.newAddress.company);
		enterAddressLine1(address.newAddress.addressLine1);
		enterAddressLine2(address.newAddress.addressLine2);
		enterCity(address.newAddress.city);
		selectCountry(address.newAddress.country);
		selectState(address.newAddress.state);
		enterZipCode(address.newAddress.zipCode);
		enterHomePhone(address.newAddress.homePhone);
		enterMobilePhone(address.newAddress.mobilePhone);
		enterAdditionalInformation(address.newAddress.additionalInformation);
		enterReferenceAddress(address.newAddress.addressReference);
		log.info("Entered the address details");
	}
	
	public boolean deleteAllSecondaryAddress() {
		log.info("Number of addresses: "+deleteButton.size());
		if(deleteButton.size()>1) {
			for(int i=deleteButton.size(); i > 1; i--) {
				driver.findElement(By.xpath(String.format(deleteAddressXpath, i))).click();
				eo.getAlertMessageAndAccept();
				eo.pause(2);
			}
		} else {
			log.info("There are no secondary addresses");
		}
		return eo.isElementPresent(By.xpath(String.format(deleteAddressXpath, 2)));
	}
	
	public String getSecondaryAddress() {
		String secondaryAddress = secondaryAddressText.getText();
		log.info("Secondary Address:\n"+secondaryAddress);
		return secondaryAddress;
	}
	
	public void clickUpdateButton() {
		updateButton.click();
		log.info("Clicked on Update button");
	}
}

