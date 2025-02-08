Feature: Get all doctors from the API

  Scenario: Fetch list of all doctors
    Given the API is running
    When I send a GET request to "/api/mobile/doctors"
    Then the response status should be 200
    And the response should contain a list of doctors
