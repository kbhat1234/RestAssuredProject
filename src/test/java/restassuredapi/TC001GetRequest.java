package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001GetRequest {
	
	@Test
	public void getUsersList() {
		//specify base URI
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
		
		//request object
		RequestSpecification method = RestAssured.given();
		
		//response object
		Response response = method.request(Method.GET);
		
		//print response in console window
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//status line validation
		String statusLine = response.getStatusLine();
		System.out.println("StatusLine is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		System.out.println(response.getHeaders().toString());
		
		String contentType =  response.header("Content-Type");
		System.out.println("Content Type is "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content encoding is "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
}