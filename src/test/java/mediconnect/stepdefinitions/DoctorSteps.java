package mediconnect.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mediconnect.utils.APIClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;



public class DoctorSteps {

    private final APIClient apiClient;
    private String responseBody;
    private HttpStatus responseStatus;
    private String doctorPayload;

    public DoctorSteps() {
        this.apiClient = new APIClient();
    }

    @Given("I have a valid doctor payload")
    public void i_have_a_valid_doctor_payload() {
        doctorPayload = "{ \"name\": \"Dr. Smith\", \"specialty\": \"Cardiology\" }";
    }

    @Given("the API is running")
    public void theApiIsRunning() {
        // Assuming the API is already running
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        responseBody = apiClient.get(endpoint);
        responseStatus = HttpStatus.OK;
    }

    @When("I send a POST request to {string} with payload")
    public void iSendAPOSTRequestToWithPayload(String endpoint, String payload) {
        responseBody = apiClient.post(endpoint, payload);
        responseStatus = HttpStatus.CREATED; // Assuming 201 for a successful POST
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        responseStatus = apiClient.delete(endpoint);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, responseStatus.value());
    }

    @Then("the response should contain a list of doctors")
    public void theResponseShouldContainAListOfDoctors() {
        Assertions.assertNotNull(responseBody, "Response body should not be null");
        Assertions.assertTrue(responseBody.contains("id"), "Response should contain doctors' id");
        Assertions.assertTrue(responseBody.contains("firstName"), "Response should contain doctors' first name");
        Assertions.assertTrue(responseBody.contains("lastName"), "Response should contain doctors' last name");
        Assertions.assertTrue(responseBody.contains("name"), "Response should contain doctors' name");
        Assertions.assertTrue(responseBody.contains("specialty"), "Response should contain doctors' specialty");
        Assertions.assertTrue(responseBody.contains("score"), "Response should contain doctors' score");
        Assertions.assertTrue(responseBody.contains("reviewCount"), "Response should contain doctors' review Count");
        Assertions.assertTrue(responseBody.contains("photo"), "Response should contain doctors' photo");
    }
}
