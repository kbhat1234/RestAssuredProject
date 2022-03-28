package restassuredapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataProviderTest {
	@Test(dataProvider="empdataprovider")
	@SuppressWarnings("unchecked")
	public void createUser(String ename, String ejob) {
		RestAssured.baseURI = "https://reqres.in/api";
		
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name",ename);
		requestParams.put("job",ejob);
		request.header("Content-Type","application/json");
		
		request.body(requestParams.toJSONString());
		
		Response response = request.request(Method.POST,"/users");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response body is "+responseBody);
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(ejob), true);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}

	@DataProvider(name="empdataprovider")
	String [][] getUserData(){
		String [][] empData = {{"karthik","qa Manager"}, {"rini","HR Executive"}, {"kaustubh","KG1"}};
		return(empData);
	}
}
