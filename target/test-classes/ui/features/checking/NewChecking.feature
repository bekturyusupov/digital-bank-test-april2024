Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "elon_mail@gmail.com" with a password "Tester123"
    When the user creates a new checking account with the following data
      | accountType       | accountOwnership | accountName           | initialDeposit |
      | Standard Checking | Individual       | Elon's Third Checking | 1000.00        |
    Then the user should see the green "Successfully created new Standard Checking account named Elon's Third Checking" message
    And the user should see newly added account card
      | accountName            | accountType       | ownership  | accountNumber | interestRate | balance |
      | Elon's Third Checking | Standard Checking | Individual | 486131037     | 0.0%         | 1000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount  | balance |
      | 2024-04-08 16:33 | Income   | 845327685 (DPT) - Deposit | 1000.00 | 1000.00 |
