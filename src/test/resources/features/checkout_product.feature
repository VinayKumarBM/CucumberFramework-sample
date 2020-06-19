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