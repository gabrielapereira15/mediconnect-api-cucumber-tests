package mediconnect.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

public class APIClient {

    private final RestClient restClient;
    private static final String BASE_URL = "http://localhost:8080";

    public APIClient() {
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String get(String endpoint) {
        try {
            return restClient.get()
                    .uri(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(String.class);
        } catch (RestClientResponseException ex) {
            return handleException(ex);
        }
    }

    public String post(String endpoint, String payload) {
        try {
            return restClient.post()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .body(String.class);
        } catch (RestClientResponseException ex) {
            return handleException(ex);
        }
    }

    public String put(String endpoint, String payload) {
        try {
            return restClient.put()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .body(String.class);
        } catch (RestClientResponseException ex) {
            return handleException(ex);
        }
    }

    public HttpStatus delete(String endpoint) {
        try {
            restClient.delete()
                    .uri(endpoint)
                    .retrieve()
                    .toBodilessEntity();
            return HttpStatus.NO_CONTENT;
        } catch (RestClientResponseException ex) {
            return HttpStatus.valueOf(ex.getStatusCode().value());
        }
    }

    private String handleException(RestClientResponseException ex) {
        return ex.getResponseBodyAsString();
    }
}
