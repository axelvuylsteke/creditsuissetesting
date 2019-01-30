package ch.creditsuisse.testing.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = {"@Sanity", "not @Ignore"},
        glue = "ch.creditsuisse.testing",
        plugin = {"pretty", "json:target/cucumber/json/Sanity.json", "html:target/cucumber/html/Sanity.html"},
        strict = true,
        snippets = SnippetType.CAMELCASE
)
public class Sanity extends AbstractTestNGCucumberTests {
}
