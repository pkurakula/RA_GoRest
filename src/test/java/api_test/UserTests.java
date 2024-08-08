package api_test;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api_endpoints.EndPoints;
import api_payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	int id;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpData() throws IOException {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setName(faker.name().fullName());
		userPayload.setGender("male");
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setStatus("inactive");
		
		//Logs
		logger = LogManager.getLogger(this.getClass());
		
		}
	
	@Test(priority=1)
	public void testPostUser() {
		logger.info("**********Execution of UserTests Started**********");
		logger.info("**********Ceating User Started**********");
		Response response = EndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		id = response.jsonPath().getInt("id");
		logger.info("**********Ceating User complete**********");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		logger.info("**********Getting User Info Started**********");
		Response response = EndPoints.getUser(id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********Ceating User Info Complete**********");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		//Update data
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setStatus("active");
		
		logger.info("**********Updating User Started**********");
		Response response = EndPoints.updateUser(userPayload, id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********Updating User Complete**********");
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		logger.info("**********Deleting User Started**********");
		Response response = EndPoints.deleteUser(id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
		logger.info("**********Deleting User Complete**********");
		logger.info("**********Execution of UserTests Complete**********");
	}
}
