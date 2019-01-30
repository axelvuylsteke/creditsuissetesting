package ch.creditsuisse.testing.steps;

import ch.creditsuisse.testing.utils.ValidateDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

import static ch.creditsuisse.testing.utils.ValidateDataUtils.checkResponse;
import static ch.creditsuisse.testing.utils.ValidateDataUtils.getValidateBatchData;
import static ch.creditsuisse.testing.utils.ValidateDataUtils.getValidateData;

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
    @Then("^I receive multiple success responses$")
    public void checkValidateBatchResponses()throws IOException {
        String response = EntityUtils.toString(validateResponse.getEntity());
        Gson gson = new Gson();
        Response[] list = gson.fromJson(response, Response[].class);
        System.out.println(list[0].getStatus());

    }
    class Response {
        private String status;
        private List<String> messages;

        public String getStatus() {
            return status;
        }

        public List<String> getMessages() {
            return messages;
        }
    }
}
