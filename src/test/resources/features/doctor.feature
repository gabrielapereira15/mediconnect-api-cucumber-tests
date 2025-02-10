Feature: Doctor API

  Scenario: Create a new doctor
    Given I have a valid doctor payload
    When I send a POST request to "/api/doctors"
    Then the response status should be 201
    And the response should contain the doctor details

  Scenario: Edit an existing doctor
    Given I have an existing doctor with ID "f423cfd9-dc53-47b5-a071-98c4cefb5bba"
    When I update the doctor's information
    Then the response status should be 200
    And the updated doctor details should be returned

  Scenario: Delete a doctor
    Given I have an existing doctor with ID "f423cfd9-dc53-47b5-a071-98c4cefb5bba"
    When I send a DELETE request to "/api/doctors/123"
    Then the response status should be 204

  Scenario: Fetch list of all doctors
    Given the API is running
    When I send a GET request to "/api/mobile/doctors"
    Then the response status should be 200
    And the response should contain a list of doctors

  Scenario: Fetch a specific doctor
    Given I have an existing doctor with ID "f423cfd9-dc53-47b5-a071-98c4cefb5bba"
    When I send a GET request to "/api/mobile/doctors/f423cfd9-dc53-47b5-a071-98c4cefb5bba"
    Then the response status should be 200
    And the response should contain the doctor's details
