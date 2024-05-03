@ExternalAccount
Feature: Adding a new external banking account

  @NegativeExternalAccountLinkCase
  Scenario: Add open banking account
    Given the user logged in as "elon_mail@gmail.com" with a password "Tester123" to create External Account
    When User links a new external banking account with the following data
      | bankingProvider | username            | password  |
      |                 | elon_mail@gmail.com | Tester123 |
    Then User should see the red "There are no banking providers available." message
    And User should see on the Open Banking Providers field the following required field error message "Please select an item in the list."

