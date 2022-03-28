package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import utils.RestUtils;

public class TestCase004 extends TestBase {
	String name = RestUtils.name();
	String job = RestUtils.job();

	@BeforeClass
	public void deleteUser() throws InterruptedException {
		logger.info("**************Test Case004 Started********************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		//JSONObject requestParams = new JSONObject();
		//requestParams.put("name", name);
		//requestParams.put("job", job);

		httpRequest.header("Content-Type", "application/json");

		//httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.DELETE, "/users/"+id);
		//response = httpRequest.request(Method.HEAD);
		Thread.sleep(2000);
	}
	
	@Test
	public void checkResponseBody() {
		logger.info("***************Checking Response body****************");
		String responseBody = response.getBody().asString();
		logger.info("Response body is " + responseBody);
		Assert.assertTrue(responseBody != null);
		//Assert.assertEquals(responseBody.contains(name), true);
		//Assert.assertEquals(responseBody.contains(job), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("*********Checking status code***************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	public void checkStatusLine() {
		logger.info("***********Checking Status Line*****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	
	@Test
	public void checkContentType() {
		logger.info("****************Checking content type********************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is "+contentType);
		Assert.assertEquals(contentType, null);
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("*******Test case002 is finished**********");
	}
}