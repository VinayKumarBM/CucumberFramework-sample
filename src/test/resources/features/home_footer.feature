@testApp @footer
Feature: This is to test features related to Contact Us page

  @newsLetterSubscription
  Scenario Outline: To verify news letter subscription
    Given I am on the application
    When I enter <email> for news letter subscription
    Then I verify that message '<message>' is displayed

    Examples: 
      | email             | message                                                           |
      | test@gmail.com    | Newsletter : This email address is already registered.            |
      | test_%s@gmail.com | Newsletter : You have successfully subscribed to this newsletter. |

  @footerLinks
  Scenario: To verify footer links
    Given I am on the application
    When I navigate to footer section
    Then I verify the contact details in Store information section of footer
      | address                                                         | email                         | contact        |
      | Selenium Framework, Research Triangle Park, North Carolina, USA | support@seleniumframework.com | (347) 466-7432 |
    And I verify Categories footer links are not broken
      | Women | You will find here all woman fashion collections. |
    And I verify My account footer links are not broken
      | My orders        | My orders                      |
      | My credit slips  | My credit slips                |
      | My addresses     | My addresses                   |
      | My personal info | Manage my personal information |
    And I verify Information footer links are not broken
      | Specials                    | Specials                    |
      | New products                | New products                |
      | Best sellers                | Best sellers                |
      | Our stores                  | Our stores                  |
      | Contact us                  | Contact us                  |
      | Terms and conditions of use | Terms and conditions of use |
      | About us                    | About us                    |
      | Sitemap                     | Sitemap                     |
    And I verify Social Media links in footer
      | Facebook    |
      | Twitter     |
      | Youtube     |
      | Google Plus |
