package ch.creditsuisse.testing.steps;

import ch.creditsuisse.testing.utils.ValidateDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;

import java.io.IOException;

import static ch.creditsuisse.testing.utils.ValidateDataUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidateBatch {
    private String validationJsonBody;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private HttpResponse validateResponse;

    @Given("^I set the ValidationBatch body with (.*)$")
    public void setValidationBatchBody(String jsonFile) throws IOException {
        validateResponse = null;
        validationJsonBody = getValidateBatchData(jsonFile, objectMapper);
    }

    @When("^I perform the /validateBatch REST call$")
    public void performValidateBatch() throws IOException{
        validateResponse = ValidateDataUtils.executeRequest("http://localhost:12345/validateBatch", validationJsonBody);
    }
    @Then("^I receive (.*) succesresponses$")
    public void checkValidateBatchResponses(String succesResponses)throws IOException {
        assertThat(Integer.parseInt(succesResponses)).isEqualTo(checkNumberOfResponses(validateResponse));
    }
    @Then("^I receive a badRequest Error$")
    public void checkBadRequestResponse(){
        assertThat(validateResponse.getStatusLine().getStatusCode()).isIn(400, 401, 402, 403, 404, 500);
    }
}
