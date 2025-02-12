Feature: Validating Place API's

Scenario: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload
	When User calls "AddPlaceAPI" with Post http request
	Then the API call got success with status code
	And "status" is Response body is "OK"
	