package Day6restapi_gson_serialization;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import cucumber.api.java.it.Ma;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/*
        Given accept type and Content type is JSON
        And request json body is:
        {
          "gender":"Male",
          "name":"Maximus",
          "phone":8877445596
       }
        When user sends POST request to '/spartans/'
        Then status code 201
        And content type should be application/json
        And json payload/response should contain:
        "A Spartan is Born!" message
        and same data what is posted
     */

public class POSTActions {

    @Test
    public void post_new_spartan_test(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "\t\"gender\":\"Male\",\n" +
                        "\t\"name\":\"peter\",\n" +
                        "\t\"phone\":7754553311\n" + "}")
                .when().post("http://3.95.173.92:8000/api/spartans/");

        //response validation
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        //extract message using jsonpath

        String message = response.body().path("success");
        assertEquals("A Spartan is Born!", message);

        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");
        assertEquals("A Spartan is Born!", message2);

        assertEquals("Male", json.getString("data.gender"));
        assertEquals("peter", json.getString("data.name"));
        assertEquals(7754553311L, json.getLong("data.phone"));

    }

    @Test
    public void post_new_spartan_with_map_test() {
        //Create a map to be used as a body for post request
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "Ronaldo");
        requestMap.put("phone", 1122334455);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("http://3.95.173.92:8000/api/spartans/");

        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("A Spartan is Born!", response.body().path("success"));

        String message = response.path("success");
        assertEquals("A Spartan is Born!", message);

        JsonPath jsonPath = response.jsonPath();
        String message2 = jsonPath.getString("success");
        assertEquals("A Spartan is Born!", message2);

        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("Ronaldo", jsonPath.getString("data.name"));
        assertEquals(1122334455, jsonPath.getLong("data.phone"));

    }
}
