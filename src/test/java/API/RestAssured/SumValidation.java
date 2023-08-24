package API.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	
	@Test
	public  void sumOfCourses() {
		//Verify if sum of all course prices matches with purchase amount
		JsonPath js=new JsonPath(payload.CoursePrice());
		
int count =js.getInt("courses.size()");
int sum=0;
for(int i=0;i<count;i++) {
	int price=js.getInt("courses["+i+"].price");
	int copies=js.getInt("courses["+i+"].copies");
	int totalamount=price*copies;
	sum+=totalamount;
//	System.out.println(sum);
}
	System.out.println(sum);
	int purchaseAmount=js.getInt("dashboard.purchaseAmount");
	Assert.assertEquals(sum,purchaseAmount);
	}
}
