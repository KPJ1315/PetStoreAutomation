package api.test;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEnpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestCases {
	Faker fakeUserData;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpUserData() { // this method generated payload data for a user to be used for creation
		fakeUserData = new Faker();
		userPayload = new User();
		userPayload.setId(fakeUserData.idNumber().hashCode()); // idNumber() will generate a random number. hashCode() ensures the number is unique.
		userPayload.setUsername(fakeUserData.name().username());
		userPayload.setFirstName(fakeUserData.name().firstName());
		userPayload.setLastName(fakeUserData.name().lastName());
		userPayload.setEmail(fakeUserData.internet().emailAddress());
		userPayload.setPassword(fakeUserData.internet().password());
		userPayload.setPhone(fakeUserData.phoneNumber().cellPhone());			
		logger = LogManager.getLogger(this.getClass());		
	}

	@Test(priority = 1)
	public void testPostUser(){
		logger.info("\nCreating user\n");
		Response response = UserEnpoints.createUser(userPayload); // here we are passing the payload of user data created in above method to create a user
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("\nCreated user\n");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		System.out.println("\nUser is fetched\n");
		Response response=UserEnpoints.getUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test(priority = 3)
	public void testPutUserByName() {	
		System.out.println("\nUser is updated\n");
		//Updating the  user details
		userPayload.setFirstName(fakeUserData.name().firstName());
		userPayload.setEmail(fakeUserData.internet().emailAddress());
		userPayload.setLastName(fakeUserData.name().lastName());

		Response response = UserEnpoints.updateUser(userPayload.getUsername(), userPayload);// user details is updated with above changes21	1
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);

		//To verify the user details is updated or not
		Response afterUpdateResponse=UserEnpoints.getUser(userPayload.getUsername());
		response.then().log().body().statusCode(200); // to verify status code
		System.out.println(afterUpdateResponse.asPrettyString());		
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		System.out.println("\n User is deleted\n");
		Response response = UserEnpoints.deleteUser(userPayload.getUsername());
		response.then().log().body().statusCode(200); // one way to check status code of request
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200); // another way to check status code of request
	}

}
