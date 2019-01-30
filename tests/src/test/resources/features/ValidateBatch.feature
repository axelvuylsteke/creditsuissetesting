@Sanity
Feature: POST validateBatch REST API call testing

  Scenario Outline: Valid REST call of /validateBatch: <testCase>
    Given I set the ValidationBatch body with <jsonBody>
    When I perform the /validateBatch REST call
    Then I receive <succesResponse> succesresponses, <errorMessages> errorresponses and <badRequest> badRequest responses

    Examples:
      | testCase              | jsonBody      | succesResponse | errorMessages | badRequest |
      | provided json example | <validBatch1> | 3              | 0             | 0          |
