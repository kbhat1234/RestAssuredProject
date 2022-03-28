package restassuredapi;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestCase005 extends TestBase {

	@BeforeClass
	public void getHeaders() throws InterruptedException {
		logger.info("**************Test Case005 Started********************");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();

		httpRequest.header("Content-Type", "application/json");

		//httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.HEAD,"/users?page=2");
		Thread.sleep(2000);
	}
	
	@Test
	public void getResponseHeaders() {
		logger.info("***********getting the headers****************");
		String headers = response.getHeaders().toString();
		logger.info("Headers is "+headers);
	}
		
	@AfterClass
	public void tearDown() {
		logger.info("*******Test case005 is finished**********");
	}
}