# creditsuissetesting

Remarks:

/validate
legalEntity -> no validation against this field. Can be any value.
valueDate -> can be in the future
Rate -> Can be overwritten so there is no corresponding rate between amount1 and amount 2.
exerciseStartDate -> No validation for this field. Can be before tradeDate and after expiryDate
Difference in error handling. Some will have a badRequest, some will have an error message with "ERROR". No real ruling behind this.
   -> more difficult to handle this.
Possible to add extra parameters. No strict JsonStructure.

/validateBatch
Missing a date field results in a Internal Server Error. Caught in the tests right now. However this should have better error handling
If trader is not part of a json element, it still passes through with a 200. In a single validate this gives a bad request.


RESULTS:
Test report will be available with command line command: mvn clean verify -Psanity
You will retrieve the report in the tests/target/cucumber-html-resports/overview-features.html
From this html page you can click through to features/steps/tags views.

Comments why 1 test is failing you can also see in the feature files itself.
In total 10 of my tests at the moment are failing. When bugfixing is done or analysis rework is done, these can be solved.
