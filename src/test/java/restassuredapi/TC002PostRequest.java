package restassuredapi;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002PostRequest {

	@SuppressWarnings("unchecked")
	@Test
	public void addUser() {
		RestAssured.baseURI = "https://reqres.in/api";
		
		RequestSpecification request = RestAssured.given();
		
		//request payload sending along with post request
		JSONObject reqParams = new JSONObject();
		reqParams.put("name","morpheus");
		reqParams.put("job","leader");
		
		request.header("Content-Type","application/json");
		
		request.body(reqParams.toJSONString());
		
		Response response = request.request(Method.POST,"/users");
		String responseBody = response.getBody().asString();
		System.out.println("Response body is "+responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is "+statusLine);
		
		//String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}
}
