import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import files.payload;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclicks123").header("Conytent_type", "application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}

}
