@Sanity
Feature:

  Scenario Outline: What a feature <testCase>
    Given I am on login page
    When I press login
    Then I am logged in

    Examples:
      | testcase |
      | success  |
