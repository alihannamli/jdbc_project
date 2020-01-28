package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class SpartanTestsWithPath {

    @BeforeClass
    public static void setUp(){
        // set base uri that is defined
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.baseuri");
    }
    /*
    Given accept type is json
    And path param id is 10
    When user sends a get request to "/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;char"
    And response payload values match the following:
            id is 10,
            name is "Lorenza",
            gender is "Female",
            phone is 3312820936
     */

    @Test
    public void spartanGetWithID_Json_Test_Using_Path(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/spartans/{id}");

        //print response json body values
        System.out.println(response.body().path("id").toString());
        System.out.println(response.body().path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.body().path("id");
        System.out.println("id is: "+id);
        String firstName = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        System.out.println("id: "+id+" name: "+firstName+" gender: "+gender+" phone"+phone);

        assertEquals(10, id);
        assertEquals("Lorenza", firstName);
        assertEquals("Female", gender);
        assertEquals(3312820936L, phone);

    }

}
