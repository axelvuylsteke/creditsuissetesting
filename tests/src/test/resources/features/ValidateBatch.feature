@Sanity
Feature: POST validateBatch REST API call testing

  Scenario Outline: Valid REST call of /validateBatch: <testCase>
    Given I set the ValidationBatch body with <jsonBody>
    When I perform the /validateBatch REST call
    Then I receive multiple success responses

    Examples:
      | testCase              | jsonBody      | fieldName1 | fieldValue1 |
      | provided json example | <validBatch1> |            |             |
