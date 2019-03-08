package themoviedb.org.projecttest;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OneMovieSmokeTest {
	

//    public String apikey = "9c61245fd1113728141e677b2f705aef";
//	public String baseurl = "https://api.themoviedb.org/3/movie/550";
	@Test
	@Parameters({"apikey","baseurl"})
	public void movieRequestok(String apikey, String baseurl) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("api_key", apikey)
				.when()
				.get(baseurl);
		int statuscode = response.getStatusCode();
		assertEquals(statuscode,200);
	}
	
	@Test
	@Parameters({"apikey","baseurl"})
	public void movieData(String apikey, String baseurl) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("api_key", apikey)
				.when()
				.get(baseurl);
		response.then().body("original_title", Matchers.is("Fight Club"));
		response.then().body("imdb_id", Matchers.is("tt0137523"));
		response.then().body("release_date", Matchers.is("1999-10-15"));
		response.then().body("revenue", Matchers.is(100853753));

		int statuscode = response.getStatusCode();
		assertEquals(statuscode,200);
		
		
	}

	
	
	
	
	

}
