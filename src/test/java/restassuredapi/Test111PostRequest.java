package restassuredapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test111PostRequest {
	public static RequestSpecification httpRequest;
	public static Response response;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createUser() throws InterruptedException {
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		
		JSONObject reqParams = new JSONObject();
		reqParams.put("name", "karthik");
		reqParams.put("job", "qa manager");
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(reqParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/users");
		
		//System.out.println(response.asString());
		Thread.sleep(2000);
	}
	
	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		System.out.println("Response body is "+responseBody);
		Assert.assertEquals(responseBody!=null, true);
		Assert.assertEquals(responseBody.contains("name"), true);
		Assert.assertEquals(responseBody.contains("job"), true);
		Assert.assertEquals(responseBody.contains("id"), true);
	}
	
	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void checkStatusLine() {
		String statusLine = response.getStatusLine();
		System.out.println("Status line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}
	
	@Test
	public void checkResponseTime() {
		long responseTime = response.getTime();
		System.out.println("Response time is "+responseTime);
		Assert.assertEquals(responseTime > 2000 && responseTime <4000, true);
	}
}