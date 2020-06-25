package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.models.CreateAccount;

public class CreateAccountPage {
	private static final Logger log = LoggerFactory.getLogger(CreateAccountPage.class);
	private WebDriver driver;
	
	public CreateAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "id_gender1")
	private WebElement title;
	
	@FindBy(id = "customer_firstname")
	private WebElement firstName;
	
	@FindBy(id = "customer_lastname")
	private WebElement lastName;
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(id = "passwd")
	private WebElement password;
	
	@FindBy(id = "address1")
	private WebElement address;
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(id = "id_state")
	private WebElement state;
	
	@FindBy(id = "postcode")
	private WebElement zipCode;
	
	@FindBy(id = "id_country")
	private WebElement country;
	
	@FindBy(id = "phone_mobile")
	private WebElement mobilePhone;
	
	@FindBy(id = "alias")
	private WebElement aliasAddress;
	
	@FindBy(id = "submitAccount")
	private WebElement register_button;
	
	@FindBy(id = "days")
	private WebElement days_dropdown;
	
	@FindBy(id = "months")
	private WebElement months_dropdown;
	
	@FindBy(id = "years")
	private WebElement years_dropdown;
	
	@FindBy(id = "old_passwd")
	private WebElement oldPassword;
	
	@FindBy(id = "confirmation")
	private WebElement confirmationPassword;
	
	@FindBy(id = "newsletter")
	private WebElement newsletter_checkbox;
	
	@FindBy(id = "optin")
	private WebElement specialOffers_checkbox;
	
	@FindBy(name = "submitIdentity")
	private WebElement saveUpdate_button;
	
	public void enterFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void enterAddress(String address) {
		this.address.clear();
		this.address.sendKeys(address);
	}
	
	public void selectCountry(String country) {
		new Select(this.country).selectByVisibleText(country);
	}
	
	public void selectState(String state) {
		new Select(this.state).selectByVisibleText(state);
	}
	
	public void enterZipCode(String zipCode) {
		this.zipCode.sendKeys(zipCode);
	}
	
	public void enterCity(String city) {
		this.city.sendKeys(city);
	}
	
	public void enterMobilePhone(String mobilePhone) {
		this.mobilePhone.sendKeys(mobilePhone);
	}
	
	public void enterAliasAddress(String aliasAddress) {
		this.aliasAddress.clear();
		this.aliasAddress.sendKeys(aliasAddress);
	}
	
	public void clickOnRegisterButton() {
		this.register_button.click();
	}
	
	public void createAccount(CreateAccount createAccount) {
		log.info("Entering account details to create an account");
		enterFirstName(createAccount.createNewAccount.firstName);
		enterLastName(createAccount.createNewAccount.lastName);
		enterPassword(createAccount.createNewAccount.password);
		enterAddress(createAccount.createNewAccount.address);
		enterCity(createAccount.createNewAccount.city);
		selectCountry(createAccount.createNewAccount.country);
		selectState(createAccount.createNewAccount.state);
		enterZipCode(createAccount.createNewAccount.zipCode);
		enterMobilePhone(createAccount.createNewAccount.mobilePhone);
		enterAliasAddress(createAccount.createNewAccount.aliasAddress);
		clickOnRegisterButton();
	}
	
	public String getEmailUsedForRegistering() {
		new ElementOperations(driver).waitForVisibilityOfElement(firstName);
		log.info("email ID used for registering: "+email.getAttribute("value"));
		return email.getAttribute("value").trim();
	}
	
	public void updateDateOfBirth(String dob) {
		String date[] = dob.split("-");
		new Select(days_dropdown).selectByValue(date[0]);
		new Select(months_dropdown).selectByValue(date[1]);
		new Select(years_dropdown).selectByValue(date[2]);
		log.info("Updated the date: "+dob);
	}
	
	public void updatePassword(String oldPassword, String newPassword) {		
		this.oldPassword.sendKeys(oldPassword);
		password.sendKeys(newPassword);
		confirmationPassword.sendKeys(newPassword);
		log.info("Updated password from "+oldPassword+" to "+newPassword);
	}
	
	public void updateSubscriptions() {
		newsletter_checkbox.click();
		specialOffers_checkbox.click();
		log.info("Opted for Newsletter: "+newsletter_checkbox.isSelected()+
				"Special Offers: "+specialOffers_checkbox.isSelected());
	}
	
	public void saveUpdates() {
		saveUpdate_button.click();
		log.info("Saved the personal information");
	}
}

