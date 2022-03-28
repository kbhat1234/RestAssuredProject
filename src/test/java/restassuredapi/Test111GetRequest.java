package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test111GetRequest {

	public static Response response;
	public static RequestSpecification httpRequest;
	
	@BeforeClass
	public void getUserDetails() throws InterruptedException {
		//specify baseUri
		RestAssured.baseURI  = "https://reqres.in/api";
		
		//create request object
		httpRequest = RestAssured.given();
		
		//create response object
		response = httpRequest.request(Method.GET,"/users/2");
		System.out.println("Response is "+response.asString());
		System.out.println(response.getHeaders());
	}
	
	@Test(priority=0)
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		System.out.println("Response body is "+responseBody);	
		Assert.assertEquals(responseBody.contains("Janet"), true);
		Assert.assertEquals(responseBody.contains("2"), true);
		Assert.assertEquals(responseBody.contains("first_name"), true);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test(priority=1)
	public void checkStatusCode() {
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority=1)
	public void checkStatusLine() {
		//status line validation
		String statusLine = response.getStatusLine();
		System.out.println("Status line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test(priority=1)
	public void checkContentType() {
		String contentType = response.getContentType();
		System.out.println("Content type is "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test(priority=1)
	public void checkContentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content encoding is "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test(priority=3)
	public void checkResponseTime() {
		long responseTime = response.getTime();
		System.out.println("Response time is "+responseTime);
		Assert.assertEquals(responseTime > 2000, true);
	}
	
	@Test
	public void checkHeaders1() {
		String responseHeaders = response.header("Via");
		System.out.println("Header - Via is "+responseHeaders);
		Assert.assertEquals(responseHeaders, "1.1 vegur");
	}
	
	@Test
	public void checkHeaders2() {
		String responseHeaders = response.header("Cache-Control");
		System.out.println("Header - Cache-Control is "+responseHeaders);
		Assert.assertEquals(responseHeaders, "max-age=14400");
	}
	
	@Test
	public void checkHeaders3() {
		String responseHeaders = response.header("Server");
		System.out.println("Header - Server is "+responseHeaders);
		Assert.assertEquals(responseHeaders, "cloudflare");
	}
	
	@Test
	public void checkHeaders4() {
		String responseHeaders = response.header("CF-Cache-Status");
		System.out.println("Header - CF-Cache-Status is "+responseHeaders);
		Assert.assertEquals(responseHeaders, "HIT");
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("End of the program");
	}
}