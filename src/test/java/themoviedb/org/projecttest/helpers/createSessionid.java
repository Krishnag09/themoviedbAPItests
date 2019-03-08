package themoviedb.org.projecttest.helpers;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class createSessionid {
		
	private String sessionid;
	
	
    public createSessionid () {
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)				
				.when()
				.get("https://api.themoviedb.org/3/movie/550?api_key=9c61245fd1113728141e677b2f705aef");
		
	}


	public String getSessionid() {
		return sessionid;
	}


	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}



	

}
