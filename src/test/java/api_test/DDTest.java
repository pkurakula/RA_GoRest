package api_test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import api_endpoints.EndPoints;
import api_payload.User;
import api_utilities.DataProviders;
import api_utilities.XLUtility;
import io.restassured.response.Response;

public class DDTest {
	int r = 1;
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String name, String gender, String email, String status) throws IOException {
		
		User userPayload = new User();
		String path = System.getProperty("user.dir")+"//testData//RestID.xlsx";
		XLUtility xl = new XLUtility(path);
		
		userPayload.setName(name);
		userPayload.setGender(gender);
		userPayload.setEmail(email);
		userPayload.setStatus(status);
		
		Response response = EndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		String id = response.jsonPath().get("id").toString();
		
		if((Integer.parseInt(id))>0) {
			xl.setCellData("Sheet1", r, 0, id);
			r++;
		}	
	}

	@Test(priority=2, dataProvider="RestIDs", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String id) {
		Response response = EndPoints.deleteUser(Integer.parseInt(id));
		Assert.assertEquals(response.getStatusCode(), 204);
	}
}

