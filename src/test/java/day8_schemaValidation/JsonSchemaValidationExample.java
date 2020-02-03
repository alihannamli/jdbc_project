package day8_schemaValidation;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidationExample {

    @Test
    public void jsonSchemaValidationOfSingleSpartanAPI(){
        given().accept(ContentType.JSON)
                .pathParam("id", 99)
                .when().get("http://3.95.173.92:8000/api/spartans/{id}")
                .then().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

        given().accept(ContentType.JSON)
                .pathParam("id", 99)
                .when().get("http://3.95.173.92:8000/api/spartans/{id}")
                .then().assertThat().body(matchesJsonSchema(new File("/Users/alihannamli/Desktop/SingleSpartanSchema.json")));
    }
}
