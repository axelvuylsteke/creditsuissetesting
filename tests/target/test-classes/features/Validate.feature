@Sanity
Feature: POST validate REST API call testing

  Scenario Outline: Valid REST call of /validate: <testCase>
    Given I set the Validation body from: <jsonBody> with following alterations:
      | <fieldName1> | <fieldValue1> |
    When I perform the /Validate REST call
    Then I receive a successfull response

    Examples:
      | testCase                | jsonBody | fieldName1  | fieldValue1    |
      | provided json example 1 | <valid1> |             |                |
      | provided json example 2 | <valid2> |             |                |
      | provided json example 3 | <valid3> |             |                |
      | customer = PLUTO2       | <valid1> | customer    | PLUTO2         |
      | legalEntity = CS Zurich | <valid1> | legalEntity | CS Zurich      |
      | legalEntity = CSZurich  | <valid1> | legalEntity | CSZurich       |
      | type = Spot             | <valid1> | type        | Spot           |
      | type = Forward          | <valid1> | type        | Forward        |
      | trader = Axel Vuylsteke | <valid1> | trader      | Axel Vuylsteke |
      | direction = SELL        | <valid1> | direction   | SELL           |
      | ccyPair = CHFEUR        | <valid1> | ccyPair     | CHFEUR         |
      | customer = PLUTO2       | <valid2> | customer    | PLUTO2         |
      | legalEntity = CS Zurich | <valid2> | legalEntity | CS Zurich      |
      | legalEntity = CSZurich  | <valid2> | legalEntity | CSZurich       |
      | trader = Axel Vuylsteke | <valid2> | trader      | Axel Vuylsteke |
      | direction = SELL        | <valid2> | direction   | SELL           |
      | ccyPair = CHFEUR        | <valid2> | ccyPair     | CHFEUR         |
      | style = EUROPEAN        | <valid2> | style       | EUROPEAN       |


  Scenario Outline: Invalid REST call of /validate: <testCase>
  Response message is Error or a response code of 400
    Given I set the Validation body from: <jsonBody> with following alterations:
      | <fieldName1> | <fieldValue1> |
    When I perform the /Validate REST call
    Then I receive the following Error: <error>

    Examples:
      | testCase                                | jsonBody               | fieldName1        | fieldValue1 | error          |
      | TradeDate after Value Date              | <tradeaftervalue>      |                   |             | <errorMessage> |
      | ValueDate in a Weekend                  | <valid1>               | valueDate         | 2019-01-05  | <errorMessage> |
      | Invalid Customer                        | <valid1>               | customer          | PLUTO3      | <errorMessage> |
      | Customer small cases                    | <valid1>               | customer          | pluto1      | <errorMessage> |
      | type = Forward                          | <valid1>               | type              | Hello       | <badRequest>   |
      | Missing field in example 1              | <missingfield1>        |                   |             | <badRequest>   |
      | Missing field in example 2              | <missingfield2>        |                   |             | <badRequest>   |
      | TradeDate equals ValueDate              | <tradeequalsvaluedate> |                   |             | <errorMessage> |
      | amount is a lettercombination           | <valid1>               | amount1           | test        | <badRequest>   |
      | direction = sell                        | <valid1>               | direction         | sell        | <badRequest>   |
      | direction = random                      | <valid1>               | direction         | random      | <badRequest>   |
      | ccyPair not part of ISO4217             | <valid1>               | ccyPair           | TRAEUR      | <errorMessage> |
      | ccyPair not valid                       | <valid1>               | ccyPair           | CHFEU       | <errorMessage> |
      | type = spot with vanillaOption jsonbody | <valid2>               | type              | spot        | <badRequest>   |
      | style = american                        | <valid2>               | style             | american    | <errorMessage> |
      | style = ASIAN                           | <valid2>               | style             | ASIAN       | <errorMessage> |
      | ExpiryDate after deliveryDate           | <valid2>               | expiryDate        | 2019-12-25  | <errorMessage> |
      | PremiumDate after deliveryDate          | <valid2>               | premiumDate       | 2019-12-25  | <errorMessage> |

      #FOLLOWING TESTS SHOULD FAIL, HOWEVER THEY SUCCEED -> HENCE FAIL TEST REPORT -> UPON BUGFIXING TESTS WILL GO GREEN
            #Extra fields are possible. IS this intended?
      | Extra Field 1                           | <extrafield1>          |                   |             | <badRequest>   |
      | Extra Field 2                           | <extrafield2>          |                   |             | <badRequest>   |
            #No validation against exerciseStartDate
      | exerciseStartDate after expiryDate      | <valid2>               | exerciseStartDate | 2019-01-07  | <errorMessage> |
      | exerciseStartDate before tradeDate      | <valid2>               | exerciseStartDate | 2016-01-05  | <errorMessage> |
            #ValueDate can be on Christmas
      | ValueDate on Christmas                  | <valid1>               | valueDate         | 2019-12-25  | <errorMessage> |
      #ValueDate on a Friday for Qatar is a weekend day. Should not pass
      | ValueDate on Friday for Qatar           | <qatar>                | valueDate         | 2019-01-11  | <errorMessage> |
            #Rate change makes amount1 and amount2 not related correctly anymore. This is successfull atm
      | rate change                             | <valid1>               | rate              | 1.4         | <badRequest>   |
            #legalEntity can be any value
      | legalEntity = Hello                     | <valid1>               | legalEntity       | Hello       | <errorMessage> |
            #Not according to ISO4217
      | payCcy = EU                             | <valid2>               | payCcy            | TRA         | <errorMessage> |

















