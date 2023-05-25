Feature: Authorization Tests

  Background:

    Given User opens 'https://epicentrk.ua/' page


  Scenario Outline: User can log in with valid credentials
    When User clicks Log in icon
    And User enters phone number '<phoneNumber>'
    And User enters password '<password>'
    And User clicks Log in button
    Then User checks that Log in icon text equals '<userName>'

    Examples:
      | phoneNumber | password   | userName |
      | phoneNumber   | password | userName   |

