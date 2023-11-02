@godaddy
Feature: Validating functionalities of GoDaddy.com site

  Background: User is landed in the site
    Given Launch url

  Scenario Outline: Open Godaddy.com and Validate Page Title
    Given user is on homepage
    When User should see the "<URL>"
    Then User should validate "<Page Title>"
    Examples:
      | URL                      | Page Title |
      | https://www.godaddy.com/ | GoDaddy    |


  Scenario Outline: Automate all the menu links of godaddy.com
    Given user is on homepage
    When User should see the "<URL>"
    Then User should validate "<Page Title>"
    Examples:
      | URL                      | Page Title |
      | https://www.godaddy.com/ | GoDaddy    |