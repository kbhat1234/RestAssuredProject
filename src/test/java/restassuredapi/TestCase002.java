package restassuredapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import utils.RestUtils;

public class TestCase002 extends TestBase {
	String name = RestUtils.name();
	String job = RestUtils.job();

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createUser() throws InterruptedException {
		logger.info("**************Test Case002 Started********************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("job", job);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/users");

		Thread.sleep(2000);
	}

	@Test
	public void checkResponseBody() {
		logger.info("***************Checking Response body****************");
		String responseBody = response.getBody().asString();
		logger.info("Response body is " + responseBody);
		Assert.assertEquals(responseBody != null, true);
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(job), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("*********Checking status code***************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void checkStatusLine() {
		logger.info("***********Checking Status Line*****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}
	
	@Test
	public void checkContentType() {
		logger.info("****************Checking content type********************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	public void checkResponseTime() {
		logger.info("****************Checking response time********************");
		long responseTime = response.getTime();
		logger.info("Response time is "+responseTime);
		Assert.assertEquals(responseTime > 2000 && responseTime <4000, true);
	}

	
	
	/*@Test
	public void checkContentEncoding() {
		logger.info("****************Checking content encoding************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		Headers headers = response.getHeaders();
		System.out.println(headers);
	}*/
	
	@AfterClass
	public void tearDown() {
		logger.info("*******Test case002 is finished**********");
	}
}