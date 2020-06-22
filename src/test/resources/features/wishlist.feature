@testApp
@wishlist
Feature: This is to test Wishlist feature
   
  @wishlist
  Scenario Outline: To create a Wishlist and add product to it and delete the wishlist
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I navigate to 'My wishlists' screen
	And I remove all wishlists
	And I create a Wishlist '<wishlistName>'
	And I view wishlist '<wishlistName>'
	Then I verify that message '<wishlistMessage>' is displayed
	And I search for items containing "<product>"
	And I navigate to product details page of '<product>'
	And I should see '<message>' on adding product to wishlist
	And I navigate to 'My wishlists' screen
	And I view wishlist '<wishlistName>'
	And I verify that 1 product is added to wishlist
	And I remove product '<product>' from wishlist
	And I view wishlist '<wishlistName>'
	And I verify that message '<wishlistMessage>' is displayed	
	And I remove all wishlists
	And I logout of application
	And I should be navigated to Authentication screen
	
    Examples:
    |userName			|password |product				|message				|wishlistName	|wishlistMessage	|
    |test321@yahoo.com	|test321  |Printed Summer Dress	|Added to your wishlist.|Test Wishlist  |No products		|