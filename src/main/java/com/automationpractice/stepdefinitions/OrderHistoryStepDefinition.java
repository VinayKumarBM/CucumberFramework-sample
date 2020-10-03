package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.automationpractice.helper.PDFUtility;
import com.automationpractice.pages.OrderHistoryPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHistoryStepDefinition {
	private WebDriver driver;
	private OrderHistoryPage orderHistoryPage;
	private PDFUtility pdfUtility;
	
	public OrderHistoryStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		orderHistoryPage = new OrderHistoryPage(driver);
		pdfUtility = new PDFUtility();
	}
	
	@Then("I verify the Order history and details")
	public void iVerifyTheOrderHistoryAndDetails() {
		String orderReference = orderHistoryPage.getOrderHistoryReference();
		String orderDate = orderHistoryPage.getOrderHistoryDate();
		float orderPrice = orderHistoryPage.getOrderHistoryPrice();
		String orderPayment = orderHistoryPage.getOrderHistoryPaymentMethod();
		String orderStatus = orderHistoryPage.getOrderHistoryStatus();
		orderHistoryPage.viewOrderDetails();
		String referenceInfo = orderHistoryPage.getReferenceInfo();
		assertTrue("Order refernce is not correct", referenceInfo.contains(orderReference));
		assertTrue("Order refernce is not correct", referenceInfo.contains(orderDate));
		assertTrue("Payment Method is not correct", orderHistoryPage.getOrderInfo().contains(orderPayment));
		assertEquals("Status did not match", orderHistoryPage.getOrderStatus(),orderStatus);
		assertNotNull(orderHistoryPage.getDeliveryAddress());
		assertNotNull(orderHistoryPage.getInvoiceAddress());
		float totalPrice = orderHistoryPage.getTotalPrice();
		int quantity = orderHistoryPage.getQuantity();
		float unitPrice = orderHistoryPage.getUnitPrice();
		assertEquals("Price is not correct", totalPrice, quantity*unitPrice, 0.00);		
		float price = orderHistoryPage.getItemPriceIncTax();
		float shippingPrice = orderHistoryPage.getShippingPrice();
		float sum = orderHistoryPage.getSumTotal();		
		assertEquals("Total Price is not correct", sum, shippingPrice + price, 0.00);
		assertEquals("Order Price did not match", sum, orderPrice, 0.00);
	}

	@When("I add additional comment to the order {string}")
	public void iAddAdditionalCommentToTheOrder(String comment) {
		orderHistoryPage.viewOrderDetails();
		orderHistoryPage.selectFirstProduct();
	    orderHistoryPage.enterComment(comment);
	    orderHistoryPage.submitComment();	    
	}
	
	@Then("I verify saved comment {string}")
	public void iVerifySavedComment(String comment) {
		assertEquals("Comment did not match", comment, orderHistoryPage.getSavedComment());
	}
	
	@When("I reorder the exsisting order")
	public void iReorderTheExsistingOrder() {
		orderHistoryPage.reorderAnOrder();
	}
	
	@When("I reorder from order details")
	public void iReorderFromOrderDetials() {
		orderHistoryPage.viewOrderDetails();
		orderHistoryPage.reorderFromOrderDetails();
	}
	
	@When("I verify the details on pdf file in order history section")
	public void iVerifyTheDetailsOnPdfFileFromOrderHistory() throws Exception {
	    String fileName = orderHistoryPage.clickOrderHistoryInvoicePDF();
	    String pdfDetails = pdfUtility.getPDFText(fileName);
	    assertEquals("PDF Page count did not match", 1, pdfUtility.getPDFPageCount(fileName));
		assertTrue("PDF file dose not contains Order Date", pdfDetails.contains(orderHistoryPage.getOrderHistoryDate()));
	    assertTrue("PDF file dose not contains Reference", pdfDetails.contains(orderHistoryPage.getOrderHistoryReference()));
	    assertTrue("PDF file dose not contains Payment Method", pdfDetails.contains(orderHistoryPage.getOrderHistoryPaymentMethod()));
	    assertTrue("PDF file dose not contains Order Price", pdfDetails.contains(String.valueOf(orderHistoryPage.getOrderHistoryPrice())));
	}
	
	@Then("I verify the details on pdf file in order details section")
	public void iVerifyTheDetailsOnPdfFileInOrderDetailsSection() throws Exception {
		orderHistoryPage.viewOrderDetails();
		String fileName = orderHistoryPage.clickOrderDetailsInvoicePDF();
	    String pdfDetails = pdfUtility.getPDFText(fileName);
	    assertEquals("PDF Page count did not match", 1, pdfUtility.getPDFPageCount(fileName));
		assertTrue("PDF file dose not contains Quantity", pdfDetails.contains(String.valueOf(orderHistoryPage.getQuantity())));
		assertTrue("PDF file dose not contains Product Name", pdfDetails.contains(String.valueOf(orderHistoryPage.getProductName())));
		assertTrue("PDF file dose not contains Product Reference", pdfDetails.contains(String.valueOf(orderHistoryPage.getProductReference())));
	    assertTrue("PDF file dose not contains Shipping Price", pdfDetails.contains(String.valueOf(orderHistoryPage.getShippingPrice())));
	    assertTrue("PDF file dose not contains Unit Price", pdfDetails.contains(String.valueOf(orderHistoryPage.getUnitPrice())));	    
	    assertTrue("PDF file dose not contains Item Price Inc Tax", pdfDetails.contains(String.valueOf(orderHistoryPage.getItemPriceIncTax())));
	    assertTrue("PDF file dose not contains Sum Total", pdfDetails.contains(String.valueOf(orderHistoryPage.getSumTotal())));
	}
}
