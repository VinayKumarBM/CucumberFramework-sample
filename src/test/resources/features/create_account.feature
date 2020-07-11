@testApp @createAccount
Feature: This is to test features related to Create Account

  @createNewAccount
  Scenario Outline: To create a new Account
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I enter valid email id to create an account
    And I enter valid details <content_firstName> and register the user
    Then I should be navigated to My account screen
    And I logout of application
    And I should be navigated to Authentication screen

    Examples: 
      | content_firstName |
      | Jon               |

  @updatePersonalInformation
  Scenario Outline: To update the personal information
    Given I am on the application
    When I navigate to Login Page by clicking on Sign In button
    And I login into application with <userName>, <password>
    And I navigate to 'My personal information' screen
    And I update the password from '<password>' to '<new_password>'
    And I update date of birth to '<dob>'
    And I update Newsletter & Special offers subscriptions
    And I update the personal information
    Then I verify that message '<message>' is displayed
    And I return back to Home page
    And I logout of application

    Examples: 
      | userName          | password | new_password | dob       | message                                                  |
      | test321@yahoo.com | test321  | test123      | 6-3-1985  | Your personal information has been successfully updated. |
      | test321@yahoo.com | test123  | test321      | 29-5-1990 | Your personal information has been successfully updated. |
