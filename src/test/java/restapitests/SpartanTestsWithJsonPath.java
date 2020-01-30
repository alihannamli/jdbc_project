package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class SpartanTestsWithJsonPath {

    @BeforeClass
    public static void setUp(){
        // set base uri that is defined
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.baseuri");
    } // this setup gives us main uri for below methods

    /*
    Given accept type is json
    And path param spartan id is 11
    When user sends a get request to /spartans/{id}
    Then status code is 200
    And content type is Json
    And  "id": 11,
         "name": "Nona",
         "gender": "Female",
         "phone": 7959094216
     */

    @Test
    public void verifySpartanInfoUsingJsonPath(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/spartans/{id}");

        // then status code is 200
        assertEquals(200, response.statusCode());
        // and content type is Json
        assertEquals("application/json;charset=UTF-8", response.contentType());

        // store response json/payload into JSONPATH object
        JsonPath json = response.jsonPath(); // this way is preferred. we took the response and convert it to Json path.
                                            // because we can easily read-extract values from Json, we can manipulate the data
        JsonPath json1 = new JsonPath(response.body().asString());

        int id = json.getInt("id"); // we pass the key name
        String name = json.getString("name");
        String gender = json.getString("gender");
        long phone = json.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(11, id);
        assertEquals("Nona", name);
        assertEquals("Female", gender);
        assertEquals(7959094216L, phone);

    }

    @Test
    public void getAStudent_cbtraining_api_jsonpath(){
        JsonPath jsonData = get("http://api.cybertektraining.com/student/2497").jsonPath(); // it sends a get request, and convert to
                                                                            //json format and directly store in Jsonpath

        String firstName = jsonData.getString("students.firstName");
        String lastName = jsonData.getString("students.lastName");

        String phone = jsonData.getString("students.contact.phone");
        String email = jsonData.getString("students.contact.emailAddress");

        String companyName = jsonData.getString("students.company.companyName");
        String companyCity = jsonData.getString("students.company.address.city"); // for any error, it will return null

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("phone = " + phone);
        System.out.println("email = " + email);
        System.out.println("companyName = " + companyName);
        System.out.println("companyCity = " + companyCity);

    }

    @Test
    public void hr_api_countries_jsonpath_list(){
        JsonPath json = get("http://100.24.59.90:1000/ords/hr/countries").jsonPath();

//        json.prettyPrint();

        List<String> countryNames = json.getList("items.country_name");
        System.out.println("countryNames = " + countryNames);
        List<String> countryIDs = json.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);

        // get countryNames of all countries in region id 2
        List<String > countriesInRegion2 = json.getList("items.findAll {it.region_id == 2 }.country_name");
        System.out.println("countriesInRegion2 = " + countriesInRegion2);

    }

}
