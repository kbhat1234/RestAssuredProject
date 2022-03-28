package baseclass;

import java.util.logging.*;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String id = "2";

	public Logger logger;

	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("User lists");
		// PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.INFO);
	}
}