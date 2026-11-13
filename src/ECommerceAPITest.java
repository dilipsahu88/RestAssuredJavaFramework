import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ECommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginRequest lr = new LoginRequest();
		lr.setUserEmail("dkslouisville23@gmail.com");
		lr.setUserPassword("Ishasipi@032523");
		RequestSpecification reqLogin = given().log().all().spec(req).body(lr);
		
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		
		//Add New product
		
		RequestSpecification addProductbaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization",token).build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductbaseReq)
		.param("productName", "Laptop")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion").param("productSubCategory", "shirt")
		.param("productPrice", "60000").param("productDescription", "Lenovo")
		.param("productFor", "Men")
		.multiPart("productImage", new File("C:/Users/Jyosh/OneDrive/Documents/Lennovolaptop.png"));
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
				.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addProductResponse);
		
		String productId = js.get("productId");
		
		//Create order
		
		RequestSpecification createOrderbaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization",token).setContentType(ContentType.JSON).build();
		
		OrderDetail od = new OrderDetail();
		od.setCountry("India");
		od.setProductOrderedId(productId);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(od);
		
		Orders ord = new Orders();
		ord.setOrders(orderDetailList);
		
			  
		RequestSpecification createOrderReq = given().log().all().spec(createOrderbaseReq).body(ord);
		
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
		
		//Delete Product
		
		RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization",token).setContentType(ContentType.JSON).build();
		
		RequestSpecification deleteProdReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId", productId);
		
		String deleteProductResponse = deleteProdReq.when().delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1= new JsonPath(deleteProductResponse);
		String actualMessage = js1.getString("message");
		System.out.println(js1.getString("message"));
		Assert.assertEquals("Product Deleted Successfully", actualMessage);
		
		
		
		
		
		
		
		
		
		

	}

}
