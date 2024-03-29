package ch.creditsuisse.testing.steps;

import ch.creditsuisse.testing.utils.GenerateValidateDataDTO;
import ch.creditsuisse.testing.utils.ValidateDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;

import java.io.IOException;

import static ch.creditsuisse.testing.utils.ValidateDataUtils.checkResponse;
import static ch.creditsuisse.testing.utils.ValidateDataUtils.getValidateData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Validate {
    private String validationJsonBody;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private HttpResponse validateResponse;


    @Given("^I set the Validation body from: (.*) with following alterations:$")
    public void setValidationBodyWithAlteration(String jsonFile, @Transpose GenerateValidateDataDTO generateValidateDataDTO) throws IOException {

        validateResponse = null;
        validationJsonBody = getValidateData(jsonFile, generateValidateDataDTO.getFieldValueOverridesMap(), objectMapper);
    }

    @When("^I perform the /Validate REST call$")
    public void performValidate() throws IOException {
        validateResponse = ValidateDataUtils.executeRequest("http://localhost:12345/validate", validationJsonBody);
    }

    @Then("^I receive a successfull response$")
    public void checkSuccessResponse() throws IOException {
        assertThat("SUCCESS").isEqualTo(checkResponse(validateResponse));
    }

    @Then("^I receive the following Error: (.*)$")
    public void checkErrorResponse(String errorMessage) throws IOException {
        switch (errorMessage) {
            case "<badRequest>":
                checkResponse(validateResponse);
                assertThat(validateResponse.getStatusLine().getStatusCode()).isIn(400, 401, 402, 403, 404);
                break;
            case "<errorMessage>":
                assertThat("ERROR").isEqualTo(checkResponse(validateResponse));
                break;

        }
    }
}
