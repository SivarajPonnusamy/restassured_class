package Day_1;

import  org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

 public class HttpRequests {
	 
	 int id;
	 
	@Test(priority=1)
	void getusers() {
		
		given()
		// https://reqres.in/api/users?page=2 --get
		// https://reqres.in/api/users --post
		// {
	   // "name": "morpheus",
	    //"job": "leader"
	//}
		
		/*{
		 * https://reqres.in/api/users/2 -->put
		 * 
    "name": "morpheus",
    "job": "zion resident"
}
		 * https://reqres.in/api/users/2 (userid) ---> delete
		 * 
		 */
		
		
		// 
		
		
		.when()
		
		    .get("https://reqres.in/api/users?page=2")
		
		.then()
		  .statusCode(200)
		  .body("page",equalTo(2))
		  .log().all();
		
	}
	@Test(priority=2)
	void createUser() {
		
		HashMap hm= new HashMap();
		hm.put("name", "Siva");
		hm.put("job", "QA Engineer");
		
		id=given() // pre-requiste..
		    .contentType("application/json")
		    .body(hm)
		
		
		.when()
		
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		//.jsonPath().getInt("id");
		
		//.then()
		 //.statusCode(201)
		 //.log().all();
		
	}
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateuser() {
		HashMap hm= new HashMap();
		hm.put("name", "Siva");
		hm.put("job", "unemployed");
		
		given() // pre-requiste..
		    .contentType("application/json")
		    .body(hm)
		
		
		.when()
		
		.put("https://reqres.in/api/users"+id)
		
		
		.then()
		
		     .statusCode(404)
		     //.body()
		     .log().all();
		}
	@Test(priority=4)
	void deleteUser() {
		given()
		
		.when()
		 .delete("https://reqres.in/api/users"+id)
		
		.then()
		.statusCode(204)
		.log().all();
	}

}
