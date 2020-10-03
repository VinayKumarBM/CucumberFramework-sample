@testApp @orderHistory
Feature: This is to test Order history and details feature

  @reorder
  Scenario Outline: To reorder product in Order History
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    And I reorder the exsisting order
    And I verify that product is in cart
    And I use the delivery address as the billing address
    And I agree to the terms of service
    And I confirm oredr by paying through check
    Then I verify that order is placed sucessfully <message>
    And I logout of application

    Examples: 
      | userName          | password | message                             |
      | test321@yahoo.com | test321  | Your order on My Store is complete. |

  @reorderFromDetails
  Scenario Outline: To reorder product from Order Details
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    And I reorder from order details
    And I verify that product is in cart
    And I use the delivery address as the billing address
    And I agree to the terms of service
    And I confirm oredr by paying through bank wire
    Then I verify that order is placed through bank wire sucessfully '<message>'
    And I logout of application

    Examples: 
      | userName          | password | message                             |
      | test321@yahoo.com | test321  | Your order on My Store is complete. |

  @orderDetails
  Scenario Outline: To add verify Order history and details
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    Then I verify the Order history and details
    And I logout of application

    Examples: 
      | userName          | password |
      | test321@yahoo.com | test321  |

  @addMessage
  Scenario Outline: To add a message to an Order
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    And I add additional comment to the order '<comment>'
    Then I verify that message '<message>' is displayed
    And I verify saved comment '<comment>'
    And I logout of application

    Examples: 
      | userName          | password | comment                          | message                   |
      | test321@yahoo.com | test321  | Wish you a very HAPPY BIRTHDAY!! | Message successfully sent |

  @orderHistoryPDF
  Scenario Outline: To verify PDF details on Order History
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    Then I verify the details on pdf file in order history section
    And I logout of application

    Examples: 
      | userName          | password |
      | test321@yahoo.com | test321  |   
      
  @orderDetailsPDF
  Scenario Outline: To verify PDF details on Order Details
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'Order history and details' screen
    Then I verify the details on pdf file in order details section
    And I logout of application

    Examples: 
      | userName          | password |
      | test321@yahoo.com | test321  |         