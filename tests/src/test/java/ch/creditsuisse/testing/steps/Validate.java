package ch.creditsuisse.testing.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Validate {
    @Given("^I am on login page$")
    public void logInPage(){
        System.out.println("On login page");
    }

    @When("^I press login$")
    public void pressLogin(){
        System.out.println("press login");
    }

    @Then("^I am logged in$")
    public void loggedIn(){
        System.out.println("Logged IN");
    }
}
