package com.automationpractice.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.framework.utilities.GetConfig;

public class ContactUsPage {
	private static final Logger log = LoggerFactory.getLogger(ContactUsPage.class);
	private WebDriver driver;
	private ElementOperations eo;

	@FindBy(id = "id_contact")
	private WebElement subjectHeadingDropdown;

	@FindBy(css = "[style=''][class$=contact-title] ")
	private WebElement headingDescText;

	@FindBy(id = "email")
	private WebElement emailTextbox;

	@FindBy(id = "id_order")
	private WebElement orderReferenceTextbox;

	@FindBy(id = "fileUpload")
	private WebElement fileUpload;
	
	@FindBy(id = "message")
	private WebElement messageTextarea;
	
	@FindBy(id = "submitMessage")
	private WebElement sendButton;
	
	public ContactUsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
	}

	public void enterMessageDetails(String subjectHeading, String email, String reference, String message) {
		new Select(subjectHeadingDropdown).selectByVisibleText(subjectHeading);
		emailTextbox.sendKeys(email);
		orderReferenceTextbox.sendKeys(reference);
		fileUpload.sendKeys(System.getProperty("user.dir")+GetConfig.getConfigProperty("attachmentPath"));
		messageTextarea.sendKeys(message);	
		log.info("Entered message details");
	}
	
	public String getSubejctDescrption() {
		eo.waitForVisibilityOfElement(headingDescText);
		String desc = headingDescText.getText().trim();
		log.info("Subject Description: "+desc);
		return desc;
	}
	
	public void sendMessage() {
		sendButton.click();
		log.info("Clicked on send message button");
	}	
}
