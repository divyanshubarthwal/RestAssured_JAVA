package API.RestAssured;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(payload.CoursePrice());
		
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchase amount
		
		int totalprice=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalprice);
		
		//Title of first course
		String title=js.getString("courses[0].title");
		System.out.println(title);
		
		//Print all courses titles and their respective prices
		
		for(int i=0;i<count;i++) {
			String coursetitle=js.get("courses["+i+"].title");
			System.out.println(coursetitle);
			
			int priceofcourse=js.getInt("courses["+i+"].price");
			System.out.println(priceofcourse);
		}
		
		
		//copies sold by RPA
		for(int i=0;i<count;i++) {
			String coursetitle=js.get("courses["+i+"].title");
			if(coursetitle.equals("RPA")) {
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(copies);
			}
		}
		
		
		//Verify if sum of all course prices matches with purchase amount
		
		
	}

}
