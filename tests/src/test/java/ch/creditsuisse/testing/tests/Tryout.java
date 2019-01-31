package ch.creditsuisse.testing.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = {"@Tryout", "not @Ignore"},
        glue = "ch.creditsuisse.testing",
        plugin = {"pretty", "json:target/cucumber/json/Tryout.json", "html:target/cucumber/html/Tryout.html"},
        strict = true,
        snippets = SnippetType.CAMELCASE
)
public class Tryout extends AbstractTestNGCucumberTests {
}
