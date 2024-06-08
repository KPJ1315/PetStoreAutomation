package api.endpoints;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;
/*
 * This class contains only CRUD operations
 * */
public class UserEnpoints2 {
	
	//To get URL's from routes.properties file
	public static ResourceBundle getUrl() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("routes"); // to load routes.properties into this class and to access it
		return resourceBundle;
	}
	
//To create user, the url takes, contentType, accept and body in the request. So those are added in given() section
	public static Response createUser(User payload) {
		
		String post_URL = getUrl().getString("post_URL"); // fetch url from routes.properties
			
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()  // takes the url which needs to be accessed
			.post(post_URL);
		return response;  // this method returns the response body
		
	}
	
	public static Response getUser(String userName) {
		
		String get_URL = getUrl().getString("get_URL"); // fetch url from routes.properties
		
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
		.when()
			.get(get_URL); // since path parameter is already attached to URL above, we are not explicitly sending it here.
		return response; // returns response of the request made.
	}
	
	public static Response updateUser(String userName,User payload) {
		
		String update_URL = getUrl().getString("update_URL"); // fetch url from routes.properties
		
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
			.body(payload)
		.when()  // takes the url which needs to be accessed
			.put(update_URL);
		return response;  // this method returns the response body
	}
	
	
	public static Response deleteUser(String userName) {
		
		String delete_URL = getUrl().getString("delete_URL"); // fetch url from routes.properties
		
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
		.when()
			.delete(delete_URL); // since path parameter is already attached to URL above, we are not explicitly sending it here.
		return response; // returns response of the request made.
	}
}
