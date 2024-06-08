package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
/*
 * This class contains only CRUD operations
 * */
public class UserEnpoints {
//To create user, the url takes, contentType, accept and body in the request. So those are added in given() section
	public static Response createUser(User payload) {
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()  // takes the url which needs to be accessed
			.post(Routes.post_URL);
		return response;  // this method returns the response body
		
	}
	
	public static Response getUser(String userName) {
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
		.when()
			.get(Routes.get_URL); // since path parameter is already attached to URL above, we are not explicitly sending it here.
		return response; // returns response of the request made.
	}
	
	public static Response updateUser(String userName,User payload) {
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
			.body(payload)
		.when()  // takes the url which needs to be accessed
			.put(Routes.update_URL);
		return response;  // this method returns the response body
	}
	
	
	public static Response deleteUser(String userName) {
		//To fetch the user, the url takes username as path parameter. So it is added to given() section
		Response response=given()
			.pathParam("username", userName) // pathname is taken from parameter passed in function call and attached to the url
		.when()
			.delete(Routes.delete_URL); // since path parameter is already attached to URL above, we are not explicitly sending it here.
		return response; // returns response of the request made.
	}
}
