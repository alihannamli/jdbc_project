package restapitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class JsonToJavaDataStructures {

    @Test
    public void convertSpartanData_to_Map(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://100.24.59.90:8000/api/spartans/33");

        Map<String, ?> spartanMap = response.body().as(Map.class);

        System.out.println("spartanMap = " + spartanMap.toString());
        System.out.println("spartanMap.get(\"id\") = " + spartanMap.get("id"));
    }




}
