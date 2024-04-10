package demo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace p=new AddPlace();
		p.setAccuracy(0);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe Park");
		myList.add("shop");
		
		p.setTypes(myList);
		
		Location l=new Location();
		l.setLat(-38.34324);
		l.setLng(-45.67671);
		
		p.setLocation(l);
		
		
	}
}
