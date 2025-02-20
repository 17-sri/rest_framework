Feature: Validating Place API's

Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" is Response body is "OK"
	
	Examples:
		|name|language|address|
		|ABCD|English |India  |
		|EFGH|kannada |Kolar  |