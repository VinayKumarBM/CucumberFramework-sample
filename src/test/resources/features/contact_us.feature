@testApp
@contactUs
Feature: This is to test features related to Contact Us page
  
  Scenario Outline: To verify Contact Us functionality
    Given I am on the application
    When I navigate to Contact Us page
    And I send a message '<subject_heading>', '<heading_desc>', '<email>', '<order_reference>', '<message>'
    Then I verify that message 'Your message has been successfully sent to our team.' is displayed   	
	
    Examples:
    |subject_heading |heading_desc									|email			|order_reference|message						 |
    |Customer service|For any question about a product, an order	|test@gmail.com	|No 1234567890	|Need to return this product	 |
    |Webmaster		 |If a technical problem occurs on this website	|test@gmail.com	|No 1234567890	|Search results are not displayed|