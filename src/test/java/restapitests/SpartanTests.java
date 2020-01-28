package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class SpartanTests {

    String spartanAllURL = "http://100.24.59.90:8000/api/spartans/";
    @Test
    public void viewAllSpartansTest(){
        Response response = RestAssured.get(spartanAllURL);
        System.out.println(response.statusCode());
//        System.out.println(response.body().asString());
//        response.body().prettyPrint();
        response.body().print();
//        response.body().prettyPeek();
    }

    /*
      Given accept type is Json
      When user sends a get request to spartanAllURL
      Then response status code is 200
      And response body should json
      And response should contain "name": "Chipotle"
     */

    @Test
    public void viewAllSpartansTest2(){
        //  Response response = RestAssured.get(spartanAllURL);
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(spartanAllURL);
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assert.assertTrue(response.body().asString().contains("Nels"));
        //Assert.assertEquals(true, response.body().asString().contains("Chipotle"));

    }

    @Test
    public void viewAllSpartansTest3(){
        //  Response response = RestAssured.get(spartanAllURL);
        given().accept(ContentType.JSON).when().get(spartanAllURL).then().statusCode(200).and().contentType("application/json;charset=UTF-8");

    }

    /*
        When users sends a get request to /spartans/4
        Then status code should be 200
        And content type should be application/json;charset=UTF-8
        And json body should contain "Fidole"
     */

    @Test
    public void viewAllSpartansTest4(){
        String path2 = "http://100.24.59.90:8000/api/spartans/3";
        Response response = get(path2);
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assert.assertTrue(response.body().asString().contains("Fidole"));
    }

    @Test
    public void getASpartanTest(){
        Response response = when().get(spartanAllURL+4);
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.body().asString().contains("John Wick"));
    }


}
