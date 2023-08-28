package files;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI="http://localhost:8080";
		
		//sessionfilter and path parameter
		
		//Login scenario
		//filter() we are passing session variable , listening rresponse which is generated
		//use before when()
	SessionFilter session=new SessionFilter();
		
	String response=	given().header("Content-Type","application/json").body("{ \"username\": \"divyanshu\", \"password\": \"divyanshu\" }")
				.log().all().filter(session).when().post("/rest/auth/1/session")
				.then().log().all().extract().response().asString();
	
	
		
		//key present in path parameter is used in post parameter
		
		given().pathParam("key","10005").log().all().header("Content-Type","application/json")
			.body("{\r\n"
				+ "    \"body\": \"This is my first comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);
	}
}
