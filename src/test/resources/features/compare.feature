@testApp @compare
Feature: Compare Products

  @compareProducts
  Scenario Outline: To compare available products
    Given I am on the application
    When I have searched for <category> category
    And I change the product view to '<view>'
    And I add <count> product to compare
    And I compare added products
    Then I verify that there are <count> products in product comparision
    And I verify social media links available
      | Tweet     | Twitter                   |
      | Share     | Facebook                  |
      | Google+   | Sign in – Google accounts |
      | Pinterest | Pinterest                 |
    And I remove all products from product comparision

    Examples: 
      | category | view | count |
      | Dresses  | List |     2 |

  @compareProductsAndView
  Scenario Outline: To compare two products and view the product with lowest price
    Given I am on the application
    When I have searched for <category> category
    And I change the product view to '<view>'
    And I add <count> product to compare
    And I compare added products
    And I 'view' the product with '<cost>' price
		Then I should see the product details page of the selected product
    
    Examples: 
      | category | view | count |cost	|
      | Dresses  | List |     2 |min	|
  
  @removeProductAddedToCompare
  Scenario Outline: To add 3 product to compare and remove 2 of them in PLP
    Given I am on the application
    When I have searched for <category> category
    And I change the product view to '<view>'
    And I add <addCount> product to compare
    Then I remove <removeCount> from <addCount> products that were added
    
    Examples: 
      | category | view | addCount |removeCount	|
      | Dresses  | List |     3		 |	2					|
          
  @productCompareLimit
  Scenario Outline: To verify that user is not allowed more than 3 product to compare
    Given I am on the application
    When I have searched for <category> category
    And I change the product view to '<view>'
    Then I should see display box with message '<message>' on adding <count> products
		
    Examples: 
      | category | view | count |message																													|
      | Dresses  | List |     4 |You cannot add more than 3 product(s) to the product comparison	|      

  @compareProductsAndCheckout
  Scenario Outline: To compare two products and checkout the product with maximum price
    Given I am on the application
    When I have searched for <category> category
    And I change the product view to '<view>'
    And I add <count> product to compare
    And I compare added products
    And I 'checkout' the product with '<cost>' price
		Then I should see that correct product is added to cart to chceckout
		And I verify that product is in cart
		And I login into application with <userName>, <password>
    And I use the delivery address as the billing address
    And I agree to the terms of service
    And I confirm oredr by paying through check
    And I verify that order is placed sucessfully <message>
    And I logout of application

    Examples: 
      | userName          | password | message                             | category | view | count |cost	|
      | test321@yahoo.com | test321  | Your order on My Store is complete. | Dresses  | List |     2 |max		|    