# creditsuissetesting

##RESULTS:
Test report will be available with command line command: mvn clean verify -Psanity
You will retrieve the report in the tests/target/cucumber-html-resports/overview-features.html
From this html page you can click through to features/steps/tags views and see the test results.

Comments why 1 test is failing you can also see in the feature files itself.
In total 10 of my tests at the moment are failing. When bugfixing is done or analysis rework is done, these can be solved.

##FAILING TESTS:
* /validate
    * 2 tests failing as we can add extra fields and still passing SUCCESS. If this is ok. Test should move to success tests, or test will go to green upon fixing
    * No Validation against exerciseStartDate. This can be before tradeDate or after expiryDate. Upon fixing, 2 test will go green
    * valueDate can be on Christmas. This is a non working day. Upon fixing this will go green.
    * valueDate can be on a Friday in Qatar. (Weekends in Qatar are Friday + Saturday) Analysis needed where weekends are in the world, in cooperation with which currencies. Upon fixing this will be green.
    * Doing a rate change. There is no corresponding rate anymore with amount1 and amount2. Is this intended. If so -> move to success folder, if not this will go green upon fixing.
    * legalEntity can be any value (for example: 'Hello'). legalEntity should only be 'CSZurich' Upon fixing this should be green.
    * payCcy can be equal to a value that is not part of ISO4217 (Source wikipedia). In test the example is value 'EU'. This passes. Upon fixing or better clarification of standards this can go to green or success tests.
    * legalEntity can be of value 'CSZurich' and 'CS Zurich'. Intended? Both are passing now. If not, test should be adjusted

* /validateBatch
    * Missing a date field results in a Internal Server Error. Caught in the tests right now. However this should have better error handling
    * If trader is not part of a json element, it still passes through with a 200 response. In a single /validate this gives a bad request. Clarification needed upon behavior


Kind regards,
Axel Vuylsteke