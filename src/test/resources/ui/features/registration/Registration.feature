@Registration
Feature: Digital Bank Registration Page

  Background:
    Given User with "elon_mail@gmail.com" is not in DB
    And User navigates to Digital Bank signup page

  @Test
  Scenario: Positive Case. As a user, I want to successfully create Digital Bank Account
    When User creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email               | password  | address     | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckBox |
      | Mr.   | Elon      | Musk     | M      | 12/12/1990 | 686-98-9898 | elon_mail@gmail.com | Tester123 | 12 Main st. | Chicago  | IL     | 60626      | US      | 2243656787 | 2243676578  | 2242676573 | true          |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then The following user info should be saved in the DataBase
      | title | firstName | lastName | gender | dob        | ssn         | email               | password  | address     | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckBox |
      | Mr.   | Elon      | Musk     | M      | 12/12/1990 | 686-98-9898 | elon_mail@gmail.com | Tester123 | 12 Main st. | Chicago  | IL     | 60626      | US      | 2243656787 | 2243676578  | 2242676573 | true          |

  @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank Admin I want to make sure users can not register without providing all valid data
    When User creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckBox   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckBox> |
    Then User should see the "<fieldWithError>" following required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob        | ssn | email | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckBox | fieldWithError | errorMessage                        |
      |       |           |          |        |            |     |       |          |         |          |        |            |         |           |             |           |               | title          | Please select an item in the list.  |
      | Mr.   |           |          |        |            |     |       |          |         |          |        |            |         |           |             |           |               | firstName      | Please fill out this field.         |
      | Mr.   | Jack      |          |        |            |     |       |          |         |          |        |            |         |           |             |           |               | lastName       | Please fill out this field.         |
      | Mr.   | Jack      | Dorsey   |        |            |     |       |          |         |          |        |            |         |           |             |           |               | gender         | Please select one of these options. |
      | Mr.   | Jack      | Dorsey   | M      |            |     |       |          |         |          |        |            |         |           |             |           |               | dob            | Please fill out this field.         |
      | Mr.   | Jack      | Dorsey   | M      | 1111/11/11 |     |       |          |         |          |        |            |         |           |             |           |               | dob            | Please match the requested format.  |