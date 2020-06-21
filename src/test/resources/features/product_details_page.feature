@testApp
@pdp
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
    |userName			|password |product				|title		  |comment			  |message																		 |
    |test321@yahoo.com	|test321  |Printed Summer Dress	|Review Title |Very good product!!|Your comment has been added and will be available once approved by a moderator|
    
  @pdpSocialMedia
  Scenario Outline: To verify Social Media Links in PDP
    Given I am on the application
	When I search for items containing "<product>"
	And I navigate to product details page of '<product>'
	Then I verify social media links on PDP
	|Tweet		|Twitter					|
	|Share		|Facebook					|
	|Google+	|Sign in – Google accounts	|
	|Pinterest	|Pinterest					|
	
  Examples:
    |product				|
    |Printed Summer Dress	|
  		
  @emailToFriend
  Scenario Outline: To send a mail about the product to friend
    Given I am on the application
	When I search for items containing "<product>"
	And I navigate to product details page of '<product>'
	And I send a mail to friend '<friendName>', '<email>' about the '<product>'
	Then I should see success <message> display box
	
    Examples:
    |product				|friendName	|email			  |message									|
    |Printed Summer Dress	|Jane Doe 	|janedoe@gmail.com|Your e-mail has been sent successfully	|
    
  @addToWishlist
  Scenario Outline: To add product to Wishlist when user is not logged in
    Given I am on the application
	When I search for items containing "<product>"
	And I navigate to product details page of '<product>'
	Then I should see '<message>' on adding product to wishlist
	
    Examples:
    |product				|message										|
    |Printed Summer Dress	|You must be logged in to manage your wishlist.	|  