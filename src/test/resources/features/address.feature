@testApp
@address
Feature: This is to test address update feature
      
  @newAddress
  Scenario Outline: To add a new address
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I navigate to 'My addresses' screen
	And I delete all the secondary addresses
	And I add new address with all the required details '<addressReference>'
	And I save the address details
	Then I verify that address details are saved as '<addressReference>'
	And I logout of application
	
    Examples:
    |userName			|password |addressReference	|message												 |
    |test321@yahoo.com	|test321  |NEW ADDRESS		|Your personal information has been successfully updated.|
#   |test321@yahoo.com	|test321  |ANOTHER ADDRESS	|Your personal information has been successfully updated.|

  @updateAddress
  Scenario Outline: To update a secondary address
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
	And I login into application with <userName>, <password>
	And I navigate to 'My addresses' screen
	And I update the address details '<addressLine2>', '<homePhone>', '<mobilePhone>', '<additionalInfo>'
	And I save the address details
	Then I verify that updated address details are saved '<addressLine2>', '<homePhone>', '<mobilePhone>', '<additionalInfo>'
	And I delete all the secondary addresses
	And I logout of application
	
    Examples:
    |userName			|password |addressLine2		|homePhone	|mobilePhone|additionalInfo		 		|
    |test321@yahoo.com	|test321  |South West block	|9876543210	|			|This is my office address	|  