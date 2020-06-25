package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.automationpractice.helper.JsonReader;
import com.automationpractice.models.Address;
import com.automationpractice.pages.AddressPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddressStepDefinition {
	private WebDriver driver;
	private JsonReader json = new JsonReader();
	private AddressPage addressPage;
	private Address address;
	
	public AddressStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		addressPage = new AddressPage(driver);
	}

	@When("I delete all the secondary addresses")
	public void iDeleteAllTheSecondaryAddresses() {
	   assertTrue("All Addressess were not deleted", !addressPage.deleteAllSecondaryAddress());
	}

	@When("I add new address with all the required details {string}")
	public void iAddNewAddressWithAllTheRequiredDetails(String addressReference) {
		address = json.getAddressByReference(addressReference);
		addressPage.clickAddNewAddressButton();
		addressPage.addNewAddress(address);
	}

	@When("I save the address details")
	public void iSaveAddressDetails() {
	    addressPage.clickSaveButton();
	}

	@Then("I verify that address details are saved as {string}")
	public void iVerifyThatAddressDetailsAreSaved(String addressReference) {
		address = json.getAddressByReference(addressReference);
		String secondaryAddress = addressPage.getSecondaryAddress();
		assertTrue(address.newAddress.addressReference+" not found", 
				secondaryAddress.contains(address.newAddress.addressReference));
		assertTrue(address.newAddress.firstName+" not found", 
				secondaryAddress.contains(address.newAddress.firstName));
		assertTrue(address.newAddress.company+" not found", 
				secondaryAddress.contains(address.newAddress.company));
		assertTrue(address.newAddress.state+" not found", 
				secondaryAddress.contains(address.newAddress.state));
		assertTrue(address.newAddress.country+" not found", 
				secondaryAddress.contains(address.newAddress.country));
	}
	
	@When("I update the address details {string}, {string}, {string}, {string}")
	public void iUpdateTheAddressDetails(String addressLine2, String homePhone, String mobilePhone, String additionalInfo) {
	    addressPage.clickUpdateButton();
	    addressPage.enterAddressLine2(addressLine2);
	    addressPage.enterHomePhone(homePhone);
	    addressPage.enterMobilePhone(mobilePhone);
	    addressPage.enterAdditionalInformation(additionalInfo);
	}

	@Then("I verify that updated address details are saved {string}, {string}, {string}, {string}")
	public void iVerifyThatUpdatedAddressDetailsAreSaved(String addressLine2, String homePhone, 
			String mobilePhone, String additionalInfo) {
		String secondaryAddress = addressPage.getSecondaryAddress();
		assertTrue(addressLine2+" not found", secondaryAddress.contains(addressLine2));
		assertTrue(homePhone+" not found", secondaryAddress.contains(homePhone));		
	}
}
