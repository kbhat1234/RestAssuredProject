package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetEmployees extends TestBase {
	
	@BeforeClass
	public void getEmployees() throws InterruptedException {
		logger.info("*******************Get all employees********************");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		httpRequest.header("Content_Type","application/json");
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(2000);
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("********checking status code******************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkStatusLine() {
		logger.info("**************checking status line******************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	
	public void responseBody() {
		logger.info("******************checking response body********************");
		String responseBody = response.body().asString();
		logger.info("Response body is "+responseBody);
		//Assert.assertEquals(responseBody.contains("name"), true);
		//Assert.assertEquals(responseBody.contains("salary"), null);
	}
}