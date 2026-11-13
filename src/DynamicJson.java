import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class DynamicJson {
	@Test(dataProvider = "BooksData")
	public void addBook(String book, String isbn, String aisle)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().header("Content-Type", "application/json")
		.body(payload.AddBook(book, isbn, aisle)).
		when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
		
	}
	
	
	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"Selenium-java", "aabbcc","4455"}, {"Playwright-Javascript","ddeeff","6677"}, {"TOSCA-Nocode","gghhii","8899"}};
	}

}
