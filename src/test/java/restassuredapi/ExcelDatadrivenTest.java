package restassuredapi;

	import java.io.IOException;

import org.json.simple.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.http.Method;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
import utils.XLUtils;

	public class ExcelDatadrivenTest {
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
		String [][] getUserData() throws IOException{
			String path = System.getProperty("user.dir")+"/src/test/java/restassuredapi//userdata.xlsx";
			int rowNum = XLUtils.getRowCount(path, "Sheet1");
			int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
			
			String [][] empdata = new String[rowNum][colCount];
			for(int i=1; i<=rowNum; i++) {
				for(int j=0; j<colCount; j++ ) {
					empdata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				}
			}
			return (empdata);
			
		}
		/*@DataProvider(name="empdataprovider")
		String [][] getUserData(){
			String [][] empData = {{"karthik","qa Manager"}, {"rini","HR Executive"}, {"kaustubh","KG1"}};
			return(empData);
		}*/
}
