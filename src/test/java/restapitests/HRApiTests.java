package restapitests;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class HRApiTests {
    /*
    Task:
- create a new class HRApiTests
- create a @Test getAllRegionsTest
- Send a get request to AllRegions API endpoint
- print status code
- print content type
- pretty print response JSON
- verify that status code is 200
- verify that content type is "application/json"
- verify that json response body contains "Americas"
- verify that json response body contains "Europe"

* try to use static imports for both restAssured and assertions
* store response into Response type variable

     */
    @Test
    public void getAllRegionsTest(){
        String hrRegionsUrl = "http://100.24.59.90:1000/ords/hr/regions/";

        Response response = get(hrRegionsUrl);
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
//        System.out.println(response.body().prettyPrint());
        response.body().prettyPrint();
        System.out.println("==================");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Americas"));
        assertTrue(response.body().asString().contains("Europe"));

    }
}
