package mediconnect.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;



public class DoctorApiSteps {

    private final RestClient restClient;
    private String responseBody;
    private HttpStatus responseStatus;

    public DoctorApiSteps() {
        String BASE_URL = "http://localhost:8080";
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Given("the API is running")
    public void theApiIsRunning() {
        // Assuming the API is already running
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        try {
            responseBody = restClient.get()
                    .uri(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(String.class);

            responseStatus = HttpStatus.OK; // Assuming a successful response
        } catch (RestClientResponseException ex) {
            responseBody = ex.getResponseBodyAsString();
            responseStatus = HttpStatus.valueOf(ex.getRawStatusCode());
        }
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, responseStatus.value());
    }

    @Then("the response should contain a list of doctors")
    public void theResponseShouldContainAListOfDoctors() {
        Assertions.assertNotNull(responseBody, "Response body should not be null");
        Assertions.assertTrue(responseBody.contains("firstName"), "Response should contain doctors' first names");
        Assertions.assertTrue(responseBody.contains("lastName"), "Response should contain doctors' last names");
    }
}
