@Sanity
Feature: POST validateBatch REST API call testing

  Scenario Outline: Valid REST call of /validateBatch: <testCase>
  Valid test cases for sending a REST API call to /validateBatch endpoint.
    Given I set the ValidationBatch body with <jsonBody>
    When I perform the /validateBatch REST call
    Then I receive <succesResponse> succesresponses

    Examples:
      | testCase                       | jsonBody      | succesResponse |
      | provided json example          | <validbatch1> | 3              |
      | 1 invalid jcustomer, 2 success | <validbatch2> | 2              |

  Scenario Outline: Invalid REST call of /validateBatch: <testCase>
  Invalid test cases for sending a REST API call to /validateBatch endpoint.
    Given I set the ValidationBatch body with <jsonBody>
    When I perform the /validateBatch REST call
    Then I receive a badRequest Error

    Examples:
      | testCase               | jsonBody        |
      | wrong type             | <invalidbatch1> |
      | missing a date field   | <invalidbatch2> |
      #Missing trader gives a valid result! Should not be the case! Bad Request when doing this in a single validate
      | missing a random field | <invalidbatch3> |
