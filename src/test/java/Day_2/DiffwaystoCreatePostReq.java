package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class DiffwaystoCreatePostReq {
	ValidatableResponse id;
	
	// post using hash map
	
	// @Test(priority=1)
	void Hashmap() {
		HashMap hm = new HashMap();
		hm.put("name", "Scott");
		hm.put("location","France");
		hm.put("phone", "123456");
		String couarr []= {"C","C++"};
		hm.put("Courses", couarr);
		
		id=given()
		.contentType("application/json")
		.body(hm)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("Courses[1]", equalTo("C++"))
		.header("Content-Type","application/json" )
		.log().all()
		;
	}
	
	// 2. Post using org.json file
	   // @Test
		void orgjsonfile() {
			
			JSONObject obj = new JSONObject();
			obj.put("name", "DavidBilla");
			obj.put("location", "Coimbatore");
			obj.put("phone", "098776");
			String courAyy[]= {"English","tamil"};
			obj.put("Courses", courAyy);
			
			id=given()
			.contentType("application/json")
			.body(obj.toString()) // here the data created using org.json need to convert into String unlike HASPMAP
			
			.when()
			.post("http://localhost:3000/students")
			
			.then()
			.statusCode(201)
			.body("name",equalTo("DavidBilla"))
			.body("location",equalTo("Coimbatore"))
			.body("phone", equalTo("098776"))
			.body("Courses[1]", equalTo("tamil"))
			.header("Content-Type","application/json" )
			.log().all()
			;
		}
	 // 3. Post using pojo class 
	   // @Test
		void pojoclass() {
			
			PlainoldJavaObject obj = new PlainoldJavaObject();
			obj.setLocation("NewDelhi");
			obj.setName("Billa Billa");
			String courses[]= {"History","Geography"};
			obj.setCourses(courses);
			obj.setPhone("97654321");
			
			id=given()
			.contentType("application/json")
			.body(obj)
			
			.when()
			.post("http://localhost:3000/students")
			
			.then()
			.statusCode(201)
			.body("name",equalTo("Billa Billa"))
			.body("location",equalTo("NewDelhi"))
			.body("phone", equalTo("97654321"))
			.body("courses[1]", equalTo("Geography"))
			.header("Content-Type","application/json" )
			.log().all()
			;
		}
	    
	   // 4. Post using external file
		    @Test
			public void externalJsonfile() throws FileNotFoundException {
				
				File f = new File("/Users/sivaraj/Automation/iquantm_product/restassured/body.json");
				FileReader fr = new FileReader(f);
				JSONTokener jt = new JSONTokener(fr);
				JSONObject obj = new JSONObject(jt);
				
				
				id=given()
				.contentType("application/json")
				.body(obj.toString()) // here too the data created using external json file need to convert into String unlike HASPMAP
				// like of json.org file....
				
				.when()
				.post("http://localhost:3000/students")
				
				.then()
				.statusCode(201)
				.body("name",equalTo("SachinPiolt"))
				.body("location",equalTo("Jaipur"))
				.body("phone", equalTo("6666666"))
				.body("courses[1]", equalTo("Congress"))
				.header("Content-Type","application/json" )
				.log().all()
				;
			}
	
	    
 //@Test(priority=2)
	void testDelete() {
		given()
		
		.when()
		.delete("http://localhost:3000/students/1c93")	
		.then() 
		.statusCode(404)
		.log().all();
		
		//System.out.println("id of the post request: "+id)
		;
	}
}
