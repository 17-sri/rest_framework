package stepDefinitions;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*; // static packages will not auto suggested by eclipse

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response responseSpec;// 80
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() throws FileNotFoundException {
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad());
	}

	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();// 81
		responseSpec = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();// 79

	}

	@Then("the API call got success with status code")
	public void the_api_call_got_success_with_status_code() {
		assertEquals(responseSpec.getStatusCode(), 200);
	}

	@Then("{string} is Response body is {string}")
	public void is_response_body_is_ok(String keyValue, String expectedValue) {
		String resp = responseSpec.asString();
		JsonPath jsonPath = new JsonPath(resp);
		assertEquals(jsonPath.get(keyValue).toString(), expectedValue);
	}

}
