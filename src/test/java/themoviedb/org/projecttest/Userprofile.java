package themoviedb.org.projecttest;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import themoviedb.org.projecttest.helpers.createSessionid;

public class Userprofile extends createSessionid {

	 @Test
	 public void UserprofileUpdate() {
		 String requesttoken;
		 
			Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.param("api_key", "9c61245fd1113728141e677b2f705aef")
					.when()
					.get("https://api.themoviedb.org/3/authentication/token/new");
			requesttoken= response.jsonPath().get("request_token");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.themoviedb.org");
			driver.get("https://www.themoviedb.org/login");
			
			WebElement usernamefield = driver.findElement(By.id("username"));
			WebElement passwordfield = driver.findElement(By.id("password"));
			WebElement loginbutton = driver.findElement(By.className("k-button"));

			usernamefield.sendKeys("krishna0902");
			passwordfield.sendKeys("Krish@1234");
			loginbutton.click();
			
			
			driver.get("https://www.themoviedb.org/authenticate/"+requesttoken);
			
			WebElement acceptbutton = driver.findElement(By.id("allow_authentication"));
			acceptbutton.click();

			//Verifying the generation of a session_id when the user authorizes the session (using the browser

			Response response2 = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.param("api_key", "9c61245fd1113728141e677b2f705aef")
					.param("request_token",requesttoken )
					.when()
					.get("https://api.themoviedb.org/3/authentication/session/new");
			
			response2.then().body("session_id", Matchers.notNullValue());
			
			String sessionid = response2.jsonPath().get("session_id");
			
			
			//Verifying the workings of the Post method via User account update
			Response response3 = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.param("api_key", "9c61245fd1113728141e677b2f705aef")
					.param("session_id",sessionid)
					.when()
					.post("https://api.themoviedb.org/3/account/favorite");
			
			response3.then().body("status_message", Matchers.is("Success"));
			int statuscode = response3.getStatusCode();
			assertEquals(statuscode,1);
			
// Given more would also add steps to verify if the movie was added in the favorites for the user using  the "Get Favorite Movies" request
 
	 }
	 
	 @Test
	 public void UserprofileUpdatewihtoutapproval() {
		 String requesttoken;
		 
			Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.param("api_key", "9c61245fd1113728141e677b2f705aef")
					.when()
					.get("https://api.themoviedb.org/3/authentication/token/new");
			
			requesttoken= response.jsonPath().get("request_token");
			
//Negative testing for the scenario where user does not physically authorize the session
			
			
			Response response2 = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.param("api_key", "9c61245fd1113728141e677b2f705aef")
					.param("request_token",requesttoken )
					.when()
					.get("https://api.themoviedb.org/3/authentication/session/new");
			
			
			
			response2.then().body("status_message", Matchers.is("Session denied."));
			response.then().body("session_id", Matchers.nullValue());
				

 
	 }
	 
	 
	 
}

	 
