package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEnpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	User userPayload = new User();

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class) // dataProvider - indicates from where the data is coming & dataProviderClass - indicates from which class the data is coming. dataProviderClass is not required if the data Provider Class is defined in same package
	public void testPostUser(String userID, String userName, String firstName, String lastName,String userEmail, String pswd, String phoneNum ) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pswd);
		userPayload.setPhone(phoneNum);
		
		System.out.println("\nUser is created\n");
		Response response = UserEnpoints.createUser(userPayload); // here we are passing the payload of user data created in above method to create a user
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
		System.out.println("\n User is deleted\n");
		Response response = UserEnpoints.deleteUser(username);
		response.then().log().body().statusCode(200); // one way to check status code of request
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
