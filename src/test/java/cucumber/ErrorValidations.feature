Feature: Error Handling Validations

  Scenario Outline: Validate Error Handling
    Given I landed on Ecommerce Page
    When Logged in into application with <username> and <password>
    Then Error message "Incorrect email or password." is displayed

    Examples:
      | username                   | password    |
      | muskan01lko@rediffmail.com | Muskan@12   |

