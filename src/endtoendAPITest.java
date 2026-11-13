import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class endtoendAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").headers("Content_Type", "application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//System.out.println("the response is" + response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		//System.out.println(placeId);
		
		//Update place address
		
		String newAddress = "70 Summer walk, UK";
		System.out.println("the New Address is:"+ newAddress);
		
		given().log().all().queryParam("key", "qaclick123").header("Content_Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//Verify the updated address details
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").header("Content_Type", "application/json")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAdress = js1.getString("address");
		System.out.println("the Actual Address is:"+ actualAdress);
		
		Assert.assertEquals(actualAdress, newAddress);
		
	}

}
