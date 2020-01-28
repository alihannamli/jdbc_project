package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class SpartanTestsWithParameters {

    @BeforeClass
    public static void setUp(){
        //set Base URI so that we dont have to type this everytime
        RestAssured.baseURI = "http://100.24.59.90:8000/api";
    }

/*
    Given no headers provided
    When Users sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

    @Test
    public void helloTest(){
        // request
        Response response = get("/hello");

        //response
        // verify status code 200
        System.out.println("status code: "+response.statusLine());
        assertEquals(200, response.statusCode());
        //verify headers
        //verify content type
        assertEquals("text/plain;charset=UTF-8", response.contentType());
        System.out.println("geHeaders "+response.getHeaders()+" getValue");
        //verify date
        System.out.println("getValue"+response.headers().getValue("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify Content-Length
        System.out.println("Content-Length: "+ response.getHeader("Content-Length"));
        assertEquals("17", response.getHeader("Content-Length"));
        //verify body data
        System.out.println("Response Payload/body: "+response.asString());
        assertEquals("Hello from Sparta", response.asString());

    }

    /*
    Given accept type is Json
    And Id parameter value is 5
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Blythe" should be in response payload
   */

    @Test
    public void getSpartanById_Positive_Path_param_test(){
        //request
        Response response = given().accept(ContentType.JSON)
                            .and().pathParam("id", 5)
                            .when().get("/spartans/{id}");
        Response response1 = given().accept(ContentType.ANY).when().get("/spartans/5");

        assertEquals(200, response1.statusCode());
        assertEquals("application/json;charset=UTF-8",response1.contentType());
        assertTrue(response1.body().asString().contains("Blythe"));
    }

    /*
    Given accept type is Json
    And Id parameter value is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json;charset=UTF-8
    And Spartan Not Found" message should be in response payload
     */
    @Test
    public void getSpartanById_Positive_Path_param_Negative_test(){
        //request
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/spartans/{id}");
       // Response response1 = given().accept(ContentType.ANY).when().get("/spartans/5");

        assertEquals(404, response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }

    /*
    Given accept type is Json
    And query parameter values are : gender|Female
	                        		nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void positiveTestWithQueryParams_search(){
       Response response =given().accept(ContentType.ANY)
                            .queryParam("gender", "Female").
                            queryParam("nameContains", "e")
                            .when().get("/spartans/search");

       Response response1 = given().accept(ContentType.JSON)
                            .queryParams("gender", "Female", "nameContains", "e")
                            .when().get("/spartans/search");

       Response response2 = given().accept(ContentType.ANY)
                            .when().get("spartans/search?gender=Female&nameContains=e");

       assertEquals(200, response.statusCode());
       assertEquals(200, response1.statusCode());
       assertEquals(200, response2.statusCode());

       assertEquals("application/json;charset=UTF-8", response.contentType());
       assertEquals("application/json;charset=UTF-8", response1.contentType());
       assertEquals("application/json;charset=UTF-8", response2.contentType());
    }


}
