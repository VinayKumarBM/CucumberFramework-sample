@testApp @pdp
Feature: This is to test product details page feature

  @writeReview
  Scenario Outline: To write a reivew for a product
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I should be navigated to My account screen
    And I search for items containing "<product>"
    And I navigate to product details page of '<product>'
    And I write a new review '<title>', '<comment>'
    Then I should see success <message> display box
    And I logout of application

    Examples: 
      | userName          | password | product              | title        | comment             | message                                                                        |
      | test321@yahoo.com | test321  | Printed Summer Dress | Review Title | Very good product!! | Your comment has been added and will be available once approved by a moderator |

  @pdpSocialMedia
  Scenario Outline: To verify Social Media Links in PDP
    Given I am on the application
    When I search for items containing "<product>"
    And I navigate to product details page of '<product>'
    Then I verify social media links available
      | Tweet     | Twitter                   |
      | Share     | Facebook                  |
      | Google+   | Sign in – Google accounts |
      | Pinterest | Pinterest                 |

    Examples: 
      | product              |
      | Printed Summer Dress |

  @emailToFriend
  Scenario Outline: To send a mail about the product to friend
    Given I am on the application
    When I search for items containing "<product>"
    And I navigate to product details page of '<product>'
    And I send a mail to friend '<friendName>', '<email>' about the '<product>'
    Then I should see success <message> display box

    Examples: 
      | product              | friendName | email             | message                                |
      | Printed Summer Dress | Jane Doe   | janedoe@gmail.com | Your e-mail has been sent successfully |
      
  @productCart
  Scenario Outline: To validate product details after adding to cart
    Given I am on the application
    When I search for items containing "<product>"
    And I navigate to product details page of '<product>'
    And I enter the product quantity '<quantity>'
    And I decrease the product quantity by <decrement>
    And I increase the product quantity by <increment>
    And I select size as '<size>' and color as '<color>'
    Then I validate the details like color '<color>' size '<size>'after adding product '<product>' to cart
    And I checout product in cart
		And I validate the details like color '<color>' size '<size>' of product '<product>' in checkout page
		And I remove product from checkout page
		And I verify that message '<message>' is displayed
		 
    Examples: 
      | product              | quantity | decrement| increment  |size	|color	|message											|
      | Printed Summer Dress | 5   			| 2 			 | 1					|L		|Orange	|Your shopping cart is empty.	|    
