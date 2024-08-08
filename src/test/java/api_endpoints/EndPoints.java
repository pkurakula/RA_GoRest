package api_endpoints;

import static io.restassured.RestAssured.given;
import api_payload.User;
import io.restassured.response.Response;

public class EndPoints {
	
	static String bearerToken = "811de48effd2ea5b3f038696343627d40dd083efbd3ce966c8c8f089101e3b86";
	
	public static Response createUser(User payload) {
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			contentType("application/json").
			body(payload).
		when().
			post(Routes.post_url);
		
		return response;
	}
	
	public static Response getUser(int id) {
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			pathParams("id", id).
		when().
			get(Routes.get_url);
		
		return response;
	}
	
public static Response updateUser(User payload, int id) {
		
		Response response = given().
			headers("Authorization", "Bearer "+bearerToken).
			contentType("application/json").
			pathParams("id", id).
			body(payload).
		when().
			put(Routes.update_url);
		
		return response;
	}

public static Response deleteUser(int id) {
	
	Response response = given().
		headers("Authorization", "Bearer "+bearerToken).
		pathParams("id", id).
	when().
		delete(Routes.delete_url);
	
	return response;
}
}