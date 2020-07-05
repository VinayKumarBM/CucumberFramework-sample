@testApp
@checkoutProduct
Feature: This is to test product checkout feature

  @loginAndcheckout
  Scenario Outline: Login and checkout the product and place order
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I should be navigated to My account screen
	And I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart
	And I use the delivery address as the billing address
	And I agree to the terms of service
	And I confirm oredr by paying through check
	Then I verify that order is placed sucessfully <message>
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product				|message							|
    |test321@yahoo.com	|test321	|Printed Summer Dress	|Your order on My Store is complete.|
     
  @checkoutAndLogin
  Scenario Outline: Checkout product and login while placing the order
    Given I am on the application
	When I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart
	And I login into application with <userName>, <password>
	And I use the delivery address as the billing address
	And I agree to the terms of service
	And I confirm oredr by paying through check
	Then I verify that order is placed sucessfully <message>
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product				|message							|
    |test321@yahoo.com	|test321	|Printed Summer Dress	|Your order on My Store is complete.|
    
  @wireLoginAndcheckout
  Scenario Outline: Login and checkout the product and place order
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I should be navigated to My account screen
	And I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart
	And I use the delivery address as the billing address
	And I agree to the terms of service
	And I confirm oredr by paying through bank wire
	Then I verify that order is placed through bank wire sucessfully '<message>'
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product						|message							|
    |test321@yahoo.com	|test321	|Faded Short Sleeve T-shirts	|Your order on My Store is complete.|
  
  @wireCheckoutAndLogin
  Scenario Outline: Checkout product and login while placing the order by paying from wire 
    Given I am on the application
	When I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart
	And I login into application with <userName>, <password>
	And I use the delivery address as the billing address
	And I agree to the terms of service
	And I confirm oredr by paying through bank wire
	Then I verify that order is placed through bank wire sucessfully '<message>'
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product	|message							|
    |test321@yahoo.com	|test321	|Blouse		|Your order on My Store is complete.|   
    
  @differentBillingAddressCheckout
  Scenario Outline: Login and checkout the product and place order with different billing and delivery address
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I should be navigated to My account screen
	And I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart	
	And I add new address '<addressReference>' to use as the billing address
	And I add a comment about my order '<comment>'
	And I proceed to shipping details of order
	And I agree to the terms of service
	And I confirm oredr by paying through check
	Then I verify that order is placed sucessfully <message>
	And I will go back to Order History screen
	And I verify the Order history and details
	And I verify saved comment '<comment>'
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product				|message							|addressReference	|comment			|
    |test321@yahoo.com	|test321	|Printed Summer Dress	|Your order on My Store is complete.|BILLING ADDRESS	|Handle with care!!	|
    
  @wireDifferentBillingAddressCheckout
  Scenario Outline: Checkout product, login and while placing the order by from wire use different billing and delivery address 
    Given I am on the application
	When I search for items containing "<product>"
	And I add the "<product>" to cart
	And I verify that "<product>" is added to cart
	And I login into application with <userName>, <password>
	And I add new address '<addressReference>' to use as the billing address
	And I add a comment about my order '<comment>'
	And I proceed to shipping details of order
	And I agree to the terms of service
	And I confirm oredr by paying through bank wire
	Then I verify that order is placed through bank wire sucessfully '<message>'
	And I will go back to Order History screen
	And I verify the Order history and details
	And I verify saved comment '<comment>'
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password	|product				|message							|addressReference	|comment			|
    |test321@yahoo.com	|test321	|Printed Chiffon Dress	|Your order on My Store is complete.|BILLING ADDRESS	|Handle with care!!	|    