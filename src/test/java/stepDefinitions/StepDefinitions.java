package stepDefinitions;

import static io.restassured.RestAssured.given; // static packages will not auto suggested by eclipse
import static org.junit.Assert.*; // we have to type our own

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response responseSpec;// 80
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")//83
	public void add_place_payload_with(String name,String language,String address) throws IOException {//83
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);//86
		System.out.println(resourceAPI.getResource());//86
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();// 81
		if(method.equalsIgnoreCase("POST")) //87
		responseSpec = res.when().post(resourceAPI.getResource());// 79
		else if(method.equalsIgnoreCase("GET")) //87
			responseSpec = res.when().get(resourceAPI.getResource());
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
