package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automationpractice.helper.ElementOperations;
import com.automationpractice.helper.PDFUtility;
import com.automationpractice.helper.StringUtility;

public class OrderHistoryPage {
	private static final Logger log = LoggerFactory.getLogger(OrderHistoryPage.class);
	private WebDriver driver;
	ElementOperations eo;
	PDFUtility pdfUtility;
	
	@FindBy(name = "id_product")
	private WebElement productDropdown;
	
	@FindBy(name = "msgText")
	private WebElement messageTextarea;
	
	@FindBy(xpath = "//button[@name='submitMessage']")
	private WebElement sendButton;
	
	@FindBy(xpath = "//h3[text()='Messages']/following-sibling::div[@class='table_block']//td[2]")
	private WebElement savedCommentText;
	
	@FindBy(css = "a.color-myaccount")
	private WebElement orderHistoryRefernceText;
	
	@FindBy(css = ".history_date")
	private WebElement orderHistoryDateText;
	
	@FindBy(css = ".history_price>.price")
	private WebElement orderHistoryPriceText;
	
	@FindBy(css = ".history_method")
	private WebElement orderHistoryPaymentText;
	
	@FindBy(css = ".history_invoice>a")
	private WebElement invoicePDFLink;
	
	@FindBy(css = ".history_state>span")
	private WebElement orderHistoryStatusText;
	
	@FindBy(css = ".history_detail>a:nth-child(1)")
	private WebElement orderHistoryDetailsButton;
	
	@FindBy(css = ".history_detail>a:nth-child(2)")
	private WebElement orderHistoryReoderLink;
	
	@FindBy(css = "#submitReorder a.button>span")
	private WebElement reorderButton;
	
	@FindBy(css = "#block-order-detail .dark>strong")
	private WebElement orderRefernceInfoText;		
	
	@FindBy(css = ".info-order")
	private WebElement orderInfoText;
	
	@FindBy(css = ".detail_step_by_step td:nth-child(2)")
	private WebElement orderStatusText;
	
	@FindBy(css = ".address.alternate_item.box")
	private WebElement deliveryAddressText;
	
	@FindBy(css = ".address.item.box")
	private WebElement invoiceAddressText;	
	
	@FindBy(css = ".return_quantity")
	private WebElement quantityText;
	
	@FindBy(css = ".item .bold label")
	private WebElement productText;
	
	@FindBy(css = ".item>td>label")
	private WebElement referenceText;
	
	@FindBy(css = "div#order-detail-content .item td:nth-child(4)")
	private WebElement unitPriceText;
	
	@FindBy(css = "div#order-detail-content .item td:nth-child(5)")
	private WebElement totalPriceText;
	
	@FindBy(css = ".item:nth-child(2)>td:nth-child(2)>span.price")
	private WebElement itemPriceIncTaxText;
	
	@FindBy(css = "#order-detail-content .price-shipping")
	private WebElement shippingPriceText;
	
	@FindBy(css = ".totalprice td:nth-child(2)")
	private WebElement totalText;
	
	@FindBy(css = ".info-order.box a")
	private WebElement downloadInvoiceAsPDFLink;
	
	public OrderHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eo = new ElementOperations(driver);
		pdfUtility = new PDFUtility();
	}

	public String getOrderHistoryReference() {
		String reference = orderHistoryRefernceText.getText();
		log.info("Reference: "+reference);
		return reference;
	}
	
	public String getOrderHistoryPaymentMethod() {
		String payment = orderHistoryPaymentText.getText();
		log.info("Payment Method: "+payment);
		return payment;
	}
	
	public String getOrderHistoryStatus() {
		String status = orderHistoryStatusText.getText();
		log.info("Order Status: "+status);
		return status;
	}
	
	public String getOrderHistoryDate() {
		String date = orderHistoryDateText.getText();
		log.info("Date: "+date);
		return date;
	}
	
	public float getOrderHistoryPrice() {
		return StringUtility.getPriceFromString(orderHistoryPriceText.getText());
	}
	
	public String getReferenceInfo() {
		eo.scrollToElement(orderRefernceInfoText);
		String ref = orderRefernceInfoText.getText();
		log.info(ref);
		return ref;
	}
	
	public String getOrderStatus() {
		String status = orderStatusText.getText();
		log.info("Status: "+status);
		return status;
	}
	
	public String getOrderInfo() {
		String info = orderInfoText.getText();
		log.info("Info: "+info);
		return info;
	}
	
	public float getTotalPrice() {
		eo.scrollToElement(totalPriceText);
		return StringUtility.getPriceFromString(totalPriceText.getText());
	}
	
	public float getSumTotal() {
		return StringUtility.getPriceFromString(totalText.getText());
	}
	
	public float getShippingPrice() {
		return StringUtility.getPriceFromString(shippingPriceText.getText());
	}
	
	public float getItemPriceIncTax() {
		return StringUtility.getPriceFromString(itemPriceIncTaxText.getText());
	}
	
	public int getQuantity() {
		return Integer.parseInt(quantityText.getText());
	}
	
	public float getUnitPrice() {
		return StringUtility.getPriceFromString(unitPriceText.getText());
	}	
	
	public void viewOrderDetails() {
		orderHistoryDetailsButton.click();
		log.info("Clicked on Order Details button");
		eo.waitForVisibilityOfElement(reorderButton);
		eo.scrollToElement(reorderButton);
	}
	
	public void selectFirstProduct() {
		eo.scrollToElement(productDropdown);
		eo.selectByIndex(productDropdown, 1);
		eo.getSelectedValue(productDropdown);
	}
	
	public void enterComment(String comment) {
		messageTextarea.sendKeys(comment);
		log.info("Entered Comment: "+comment);
	}
	
	public void submitComment() {
		sendButton.click();
		log.info("Clicked on Send comment button");
	}
	
	public String getSavedComment() {
		String savedComment = savedCommentText.getText();
		log.info("Saved Comment: "+savedComment);
		return savedComment;
	}
	
	public String getProductName() {
		String productName = productText.getText().trim();
		log.info("Product Name: "+productName);
		return productName;
	}
	
	public String getProductReference() {
		String reference = referenceText.getText().trim();
		log.info("Reference: "+reference);
		return reference;
	}
	
	public String getDeliveryAddress() {
		eo.scrollToElement(deliveryAddressText);
		String deliveryAddress = deliveryAddressText.getText();
		log.info("Delivery Address"+deliveryAddress);
		return deliveryAddress;
	}
	
	public String getInvoiceAddress() {
		eo.scrollToElement(invoiceAddressText);
		String invoiceAddress = invoiceAddressText.getText();
		log.info("Invoice Address"+invoiceAddress);
		return invoiceAddress;
	}
	
	public void reorderAnOrder() {
		orderHistoryReoderLink.click();
		log.info("Clicked on Reorder link");
	}
	
	public void reorderFromOrderDetails() {
		reorderButton.click();
		log.info("Clicked on Reorder button");
	}
	
	public String clickOrderHistoryInvoicePDF() throws Exception {
		invoicePDFLink.click();
		log.info("Clicked on PDF link on Order History");
		return pdfUtility.waitForFileDownload();
	}
	
	public String clickOrderDetailsInvoicePDF() throws Exception {
		downloadInvoiceAsPDFLink.click();
		log.info("Clicked on Download your invoice as a PDF file link");
		return pdfUtility.waitForFileDownload();
	}
}
