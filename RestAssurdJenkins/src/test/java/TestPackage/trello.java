package TestPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
public class trello {

//	public will create variable that we can use in all packages
	public static String baseURL = "https://api.trello.com" ;
	public static String API_key = "d2d20b2697a1502509d591175d94574d" ;
	public static String token = "ATTAdaacb3a7daaffb752ab9d85f3e0e4a2377a6661886493991c981f7e27d60df5576B8B4F8" ;
	public static String id ;
	public static String updateName = "{ \"name\": \"AdityaUpdated\" }" ;
	
//	given : request => url, body, querypara, headers 
//	when : resource => /user , /board
//	then : response => Assertion - stringFormet - jsonPath
	@Test(priority = 0)
	public void createBoard() {
		
		RestAssured.baseURI = baseURL ;
		
	    Response response = 	given().queryParam("name", "AdityaDO")
		 .queryParam("key", API_key)
		 .queryParam("token" , token)
		 .header("Content-Type" , "application/json" )
		
		.when()
		 .post("/1/boards")
		
		.then()
		 .assertThat()
		  .statusCode(200)
		  .contentType(ContentType.JSON) 
		.extract().response() ;
	
	String result = response.asPrettyString() ;
	System.out.println(result);
	
	
	
	JsonPath js = new JsonPath(result);
	 id = js.get("id") ;
	System.out.println(id);
	}
	
	@Test(priority = 1)
	public void updateBoard() {
		RestAssured.baseURI = baseURL ;
RestAssured.baseURI = baseURL ;
		
	    Response response = 	given()
		 .queryParam("key", API_key)
		 .queryParam("token" , token)
		 .header("Content-Type" , "application/json" )
		 .body( updateName )
		.when()
		 .put("/1/boards/" + id)
		
		.then()
		 .assertThat()
		  .statusCode(200)
		  .contentType(ContentType.JSON) 
		.extract().response() ;
		
		String result = response.asPrettyString() ;
		System.out.println(result);
		
		
		
		JsonPath js = new JsonPath(result);
		 String name = js.get("name") ;
		System.out.println(name);
	}
	
	
	
	
}