package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification requestSpec;

	public RequestSpecification requestSpecification() throws IOException {
		if(requestSpec==null) { //85
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))// 81
				.addFilter(ResponseLoggingFilter.logResponseTo(log))// 81
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return requestSpec;
		}
		return requestSpec; //85
	}

	public static String getGlobalValue(String key) throws IOException {//82
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\Admin\\eclipse-workspace\\rest_framework\\src\\test\\java\\resources\\global.properties");
		properties.load(fileInputStream);
		return properties.getProperty("baseUrl");
	}

}
