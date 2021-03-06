package restapitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchers_apiTests {
    @Test
    public void getSpartanAnd_assertUsing_hamcrest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://100.24.59.90:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id",equalTo(15),
                                    "name", equalTo("Meta"),
                                            "gender" , equalTo("Female"),
                                            "phone",equalTo(1938695106)
                                    //1st part is path  2nd part is hamcrest assertion
        );

    }

    @Test
    public void teacherData_test_using_hamcrest(){
        given().accept(ContentType.JSON)
                .pathParam("name","Esen")
                .when().get("http://api.cybertektraining.com/teacher/name/{name}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("teachers.firstName",contains("Esen"),
                                    "teachers.lastName",contains("Niiazov"),
                                            "teachers.emailAddress",contains("eniiazov@gmail.com"))
                                            .log().all();
    }

    @Test
    public void teachersAllDataTest_using_hamcrest_list_assertion(){
        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .and().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("teachers.firstName",hasItems("Esen", "Albina"));

    }

}
