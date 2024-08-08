package api_endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api_payload.User;
import io.restassured.response.Response;

public class EndPoints2 {
	
	static String bearerToken = "811de48effd2ea5b3f038696343627d40dd083efbd3ce966c8c8f089101e3b86";
	
	//Get URLs from config.properties file
	static ResourceBundle getUrl() {
		ResourceBundle config = ResourceBundle.getBundle("config");  //Loads properties file
		return config;
	}
	
	public static Response createUser(User payload) {
		
		String post_url = getUrl().getString("post_url");
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			contentType("application/json").
			body(payload).
		when().
			post(post_url);
		
		return response;
	}
	
	public static Response getUser(int id) {
		
		String get_url = getUrl().getString("get_url");
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			pathParams("id", id).
		when().
			get(get_url);
		
		return response;
	}
	
	public static Response updateUser(User payload, int id) {
	
		String update_url = getUrl().getString("update_url");
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			contentType("application/json").
			pathParams("id", id).
			body(payload).
		when().
			put(update_url);
		
		return response;
	}

	public static Response deleteUser(int id) {
		
		String delete_url = getUrl().getString("delete_url");
	
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			pathParams("id", id).
		when().
			delete(delete_url);
		
		return response;
}
}