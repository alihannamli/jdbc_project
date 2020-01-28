package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class HRAPITESTSWithParameters {

    @BeforeClass
    public static void setUp(){
        // set base uri that is defined
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi.baseuri");

    }
    /*
       Given accept type is Json
       And parameters: q = region_id=2
       When users sends a GET request to "/countries"
       Then status code is 200
       And Content type is application/json
       And Payload should contain "United States of America"
       {"region_id":2}
    */
    @Test
    public void getCountries_using_queryParameters(){

        Response response = given().accept(ContentType.JSON)
                            .when().queryParam("q", "{\"region_id\":2}")
                            .and().get("/countries");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @Test
    public void getEmployees_using_queryParameters(){

        Response response = given().accept(ContentType.JSON)
                .when().queryParam("q", "{\"JOB_ID\":\"IT_PROG\"}")
                .and().get("/employees");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Diana"));

    }
}
