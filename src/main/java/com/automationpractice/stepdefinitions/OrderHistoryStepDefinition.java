package com.automationpractice.stepdefinitions;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

import com.automationpractice.pages.OrderHistoryPage;
import com.framework.utilities.DriverManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderHistoryStepDefinition {
	private WebDriver driver;
	private OrderHistoryPage orderHistoryPage;
	
	public OrderHistoryStepDefinition() {
		driver = DriverManager.getInstance().getDriver();
		orderHistoryPage = new OrderHistoryPage(driver);
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
}
