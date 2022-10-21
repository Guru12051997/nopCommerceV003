Feature: Login nopCommerce Application

  @Sanity
  Scenario: SuccessFul Login with Valid  Credentials
    Given User Launch the Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page title Should be "Dashboard / nopCommerce administration"
    When User Click on Logout Link
    Then Page Title  Should bee "Your store. Login"
    And Close/Quit the browser

  @Regression
  Scenario Outline: Data Driven Login
    Given User Launch the Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "<Email>" and Password as "<Password>"
    And Click on Login
    Then Page title Should be "Dashboard / nopCommerce administration"
    When User Click on Logout Link
    Then Page Title  Should bee "Your store. Login"
    And Close/Quit the browser

    Examples: 
      | Email               |  | Password |
      | admin@yourstore.com |  | admin    |
      | admin@yourstore.com |  | admin |
