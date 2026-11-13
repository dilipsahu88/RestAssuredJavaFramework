import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://dilipsahu88.atlassian.net";
		String createIssueResponse = given().log().all()
		.header("Content-Type", "application/json")
		.header("Authorization","Basic ZGlsaXAuc2FodTg4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAzV0hwRlpXaUZ0aFJqTTVla05KZk9QVDh2Uk1wY0F3NjFlN1hvVi1iNmhnNDhwc1dKWkF5dmFvdUJpVlRlRm15WmhFNmVZQjE2QXdQaWNOTE1ZWTR2NHFMaUFzdTF6aGM5ZjNvS2l6djFHbVVSdC1Od2YzSFpJYm1ZVDB2TGFxYTE1elA4M2pZLTdNaEx3YU05dUZXblFoaU5WUzRzQTdWcDRmNVlCdGFzSms9MTFDNThCNzM= ")
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"Enrollment button not owkring\",\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    },\r\n"
				+ "    \"description\": {\r\n"
				+ "      \"type\": \"doc\",\r\n"
				+ "      \"version\": 1,\r\n"
				+ "      \"content\": [\r\n"
				+ "        {\r\n"
				+ "          \"type\": \"paragraph\",\r\n"
				+ "          \"content\": [\r\n"
				+ "            {\r\n"
				+ "              \"type\": \"text\",\r\n"
				+ "              \"text\": \"The enrollment form is not loading causing issue enrolling new active patients\"\r\n"
				+ "            }\r\n"
				+ "          ]\r\n"
				+ "        }\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"priority\": {\r\n"
				+ "      \"name\": \"High\"\r\n"
				+ "    },\r\n"
				+ "    \"labels\": [\r\n"
				+ "      \"mobile\",\r\n"
				+ "      \"regression\"\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "")
		.log().all()
		.post("/rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		int issueId = js.getInt("id");
		System.out.println(issueId);
		
		
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic ZGlsaXAuc2FodTg4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAzV0hwRlpXaUZ0aFJqTTVla05KZk9QVDh2Uk1wY0F3NjFlN1hvVi1iNmhnNDhwc1dKWkF5dmFvdUJpVlRlRm15WmhFNmVZQjE2QXdQaWNOTE1ZWTR2NHFMaUFzdTF6aGM5ZjNvS2l6djFHbVVSdC1Od2YzSFpJYm1ZVDB2TGFxYTE1elA4M2pZLTdNaEx3YU05dUZXblFoaU5WUzRzQTdWcDRmNVlCdGFzSms9MTFDNThCNzM=")
		.multiPart("file", new File("C:/Users/Jyosh/Downloads/testscreenshot.pdf")).log().all()
		.post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

	}
}
