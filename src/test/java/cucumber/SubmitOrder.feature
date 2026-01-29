Feature: Purchase the Order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page


  @E2ERegression
  Scenario Outline: Positive test of Submitting Order
    Given Logged in into application with <username> and <password>
    When I add <productname> in Cart
    And click Checkout and submit the order
    Then confirmation message displayed on Confirmation Page

    Examples:
      | username                      | password    | productname     |
      | muskan01lko@rediffmail.com    | Muskan@123  | ADIDAS ORIGINAL |
      | muskanv01lko@gmail.com        | Mv@12345678 | ADIDAS ORIGINAL |
      | example2001@gmail.com         | John@123    | ZARA COAT 3     |