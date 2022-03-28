package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestCase001 extends TestBase {
	
	@BeforeClass
	public void getAllUsers() throws InterruptedException {
		logger.info("*******Started TestCase001*************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/users?page=2");
		logger.info(response.getHeaders().toString());
		Thread.sleep(3);
	}

	@Test
	public void checkResponseBody() {
		logger.info("************Checking Response body****************");
		String responseBody = response.getBody().asString();
		logger.info("Response body is "+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("**************Checking status code******************");
		int statusCode = response.getStatusCode();
		logger.info("Status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkStatusLine() {
		logger.info("***************Checking status line********************");
		String statusLine = response.statusLine();
		logger.info("Status Line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void checkResponeTime() {
		logger.info("*************Checking response time******************");
		long responseTime = response.getTime();
		logger.info("Response time is "+responseTime);
		if(responseTime>2000)
			logger.warning("Response time is greater than 2000");
		
		Assert.assertTrue(responseTime>2000);		
	}
	
	@Test
	public void checkContentType() {
		logger.info("*******************Checking content type*****************");
		String contentType = response.getContentType();
		logger.info("Content-Type is "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	public void checkServerType() {
		logger.info("*******************Checking server type*******************");
		String serverType = response.header("via");
		logger.info("Server Type is "+serverType);
		Assert.assertEquals(serverType, "1.1 vegur");
	}
	
	@Test
	public void checkContentEncoding() {
		logger.info("*******************Checking server encoding****************");
		String contentEncoding = response.header("content-encoding");
		logger.info("Content Encoding is "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	public void checkCookies() {
		logger.info("********************Checking cookies******************");
		String cookie = response.getCookie("welcome");
		logger.info("Cookie is "+cookie);
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("******************Test case completed********************");
	}
}