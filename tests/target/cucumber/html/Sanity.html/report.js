$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/Validate.feature");
formatter.feature({
  "name": "",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Sanity"
    }
  ]
});
formatter.scenarioOutline({
  "name": "What a feature \u003ctestCase\u003e",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on login page",
  "keyword": "Given "
});
formatter.step({
  "name": "I press login",
  "keyword": "When "
});
formatter.step({
  "name": "I am logged in",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "testcase"
      ]
    },
    {
      "cells": [
        "success"
      ]
    }
  ]
});
formatter.scenario({
  "name": "What a feature \u003ctestCase\u003e",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Sanity"
    }
  ]
});
formatter.step({
  "name": "I am on login page",
  "keyword": "Given "
});
formatter.match({
  "location": "Validate.logInPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I press login",
  "keyword": "When "
});
formatter.match({
  "location": "Validate.pressLogin()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am logged in",
  "keyword": "Then "
});
formatter.match({
  "location": "Validate.loggedIn()"
});
formatter.result({
  "status": "passed"
});
});