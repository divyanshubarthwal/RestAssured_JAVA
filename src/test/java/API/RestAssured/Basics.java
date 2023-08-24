package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {
public static void main(String[] args) {
	//validate if add place API is working as expected
	
	//given- all input details
	//when - submit the API - resource, http method
	//Then - validate the response
	//add package manually on top import static io.restassured.RestAssured.*;
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(payload.AddPlace()).when().post("maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract()
			.response().asString();
	
	System.out.println(response);
	
	//Takes string as input and convert into json and parse the json
	JsonPath js=new JsonPath(response);
	String placeId=js.getString("place_id");
	
	System.out.println(placeId);
	
	
	
	//Update place
	
	String newAddress="70 Summer walk, Africa";
	
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "").when().put("/maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	
	//Get Place
	//mention header only when we are adding body
	String getPlaceResponse=given().log().all().queryParam("key","qaclick123")
	.queryParam("place_id",placeId)
	.when().get("/maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	
	JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
	String actualAddress=js1.getString("address");
	System.out.println(actualAddress);
	
	Assert.assertEquals(newAddress, actualAddress);
	
		//Add Place->Update place with new address-> get place to validate of new address is present in response
	
		
}
	
	
}
