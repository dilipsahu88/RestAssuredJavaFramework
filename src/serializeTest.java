import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("123 Main Street louisville");
		ap.setLanguage("French-IN");
		ap.setPhone_number("(+91) 983 383 2424");
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setName("Dilip");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res = given().log().all().queryParam("key", "qaclicks123").header("Conytent_type", "application/json")
		.body(ap).when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);

	}

}
