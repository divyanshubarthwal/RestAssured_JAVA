package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		
		//dynamically build json payload with external data input
		String response=given().log().all().header("Content-Type","application/json").body(payload.Addbook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=ReusableMethods.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		
		
	}
	
	//parameterizing api tests with multiple data sets
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//Array- collections of elements  new object[]{1,2,3}
		//Multidimensional Array- collection of arrays
		return new Object[][] {
			 {"dsadsa","123"},{"weza","122333"},{"cvaswrg","2571"}
		};
	}
}
