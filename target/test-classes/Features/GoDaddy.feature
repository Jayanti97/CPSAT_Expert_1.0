@godaddy
Feature: Validating functionalities of GoDaddy.com site

  Scenario Outline: Open Godaddy.com and Validate Page Title
    Given Launch url
    When User should see the "<URL>"
    Then User should validate "<Page Title>"
    Examples:
      | URL                      | Page Title |
      | https://www.godaddy.com/ | GoDaddy    |