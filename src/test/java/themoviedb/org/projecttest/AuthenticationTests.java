package themoviedb.org.projecttest;
import static org.testng.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class AuthenticationTests {
	
//    public String apikey = "9c61245fd1113728141e677b2f705aef";
//	public String baseurl = "https://api.themoviedb.org/";
//	public String invalidapikey = "9c61245fd1113728141e677b2f705ae";


	@Test
	@Parameters({"apikey","baseurl"})
	
	public void authenticationHappyPath( String apikey, String baseurl) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("api_key",apikey )
				.when()
				.get(baseurl);
		int statuscode = response.getStatusCode();
		assertEquals(statuscode,200);
	}
	
	@Test
	@Parameters({"invalidapikey","baseurl"})

	public void authenticationInvalidKey(String invalidapikey, String baseurl  ) {
		
		
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("api_key", "cccc")
				.when()
				.get(baseurl);
		
		int statuscode = response.getStatusCode();
		assertEquals(statuscode,401);
		
		
	}
	
	@Test
	@Parameters({"apikey","baseurl"})
	public void checkRequestToken(String apikey, String baseurl) {
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("api_key", apikey)
				.when()
				.get(baseurl);
		response.then().body("request_token", Matchers.notNullValue());

		
	}
	
	
		
	}
	
	

