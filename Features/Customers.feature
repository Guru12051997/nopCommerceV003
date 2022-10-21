Feature: Customers

  Background: Common Steps
    Given User Launch the Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
  @Sanity
  Scenario: Add new Customer
    Then User can view Dashboard
    When User click on customer menu
    And Click on customer menu Items
    And Click on Add new button
    Then User can view Add new Customer Page
    When User enter customer info
    And Click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And Close/Quit the browser
  @Regression
  Scenario: Search Customer by EmailId
    Then User can view Dashboard
    When User click on customer menu
    And Click on customer menu Items
    And Enter customer Email
    When Click on Search button
    Then User Should found Email in the Search table
    And Close/Quit the browser
  @Regression
  Scenario: Search Customer by Name
    Then User can view Dashboard
    When User click on customer menu
    And Click on customer menu Items
    And Enter Customer FirstName
    And Enter Customer LastName
    When Click on Search button
    Then User Should found Name in the Search table
    And Close/Quit the browser
