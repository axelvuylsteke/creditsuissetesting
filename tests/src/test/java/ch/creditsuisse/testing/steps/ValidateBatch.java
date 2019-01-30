package ch.creditsuisse.testing.steps;

import ch.creditsuisse.testing.utils.ValidateDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import static ch.creditsuisse.testing.utils.ValidateDataUtils.*;

public class ValidateBatch {
    private String validationJsonBody;
    private String responseBody;
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpResponse validateResponse;
    private HttpPost validateRequest;

    @Given("^I set the ValidationBatch body with (.*)$")
    public void setValidationBatchBody(String jsonFile) throws IOException {
        validateRequest = null;
        validateResponse = null;
        responseBody = null;
        validationJsonBody = getValidateBatchData(jsonFile, objectMapper);
    }

    @When("^I perform the /validateBatch REST call$")
    public void performValidateBatch() throws IOException{
        validateResponse = ValidateDataUtils.executeRequest("http://localhost:12345/validateBatch", validationJsonBody);
    }
    @Then("^I receive (.*) succesresponses, (.*) errorresponses and (.*) badRequest responses$")
    public void checkValidateBatchResponses(String s, String a, String b)throws IOException {
        int success = checkNumberOfResponses("success", validateResponse);
        int badRequest = checkNumberOfResponses("badRequest", validateResponse);
        int errorMessage = checkNumberOfResponses("errorMessage", validateResponse);
        System.out.println("success: " + success);
        System.out.println("BR: " + badRequest);
        System.out.println("EM: " + errorMessage);
    }
}
