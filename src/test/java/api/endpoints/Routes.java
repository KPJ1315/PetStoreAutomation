package api.endpoints;

/*
 * This calss is created to store only URL's used for RestAPI automation.
 *  Swagger URL: https://petstore.swagger.io
 *  Create User(Post): https://petstore.swagger.io/v2/user
 *  Get User(Get): https://petstore.swagger.io/v2/user/{username}
 *  Update User(Put): https://petstore.swagger.io/v2/user/{username}
 *  Delete User(Delete): https://petstore.swagger.io/v2/user/{username}
 * */

public class Routes {
	
	public static String base_URL = "https://petstore.swagger.io/v2";
	
	//To automate User Model rest api's
	public static String post_URL= base_URL+"/user";
	public static String get_URL= base_URL+"/user/{username}";
	public static String update_URL= base_URL+"/user/{username}";
	public static String delete_URL= base_URL+"/user/{username}";
	
	//To automate Store Model rest api's
	
	//To automate Pet Model rest api's
}
