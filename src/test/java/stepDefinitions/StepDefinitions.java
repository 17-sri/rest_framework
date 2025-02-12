package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*; // static packages will not auto suggested by eclipse
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefinitions {
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response responseSpec;
	@Given("Add Place Payload")
	public void add_place_payload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setLanguage("French-IN");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://rahulshettyacademy.com");
		addPlace.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		addPlace.setTypes(myList);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		res = given().spec(requestSpec).body(addPlace);
	}

	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		responseSpec = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();//79
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
