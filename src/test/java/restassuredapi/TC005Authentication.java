package restassuredapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005Authentication {

	@Test
	public void basicAuthorizationTest() {
		RestAssured.baseURI = "https://httpbin.org/basic-auth";

		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		
		auth.setUserName("user");
		auth.setPassword("pass");
		RestAssured.authentication = auth;
		
		// request object
		RequestSpecification method = RestAssured.given();

		// response object
		Response response = method.request(Method.GET,"/kbhat/password");
		System.out.println(response.statusCode());
	}

}
