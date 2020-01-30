package Day6restapi_gson_serialization;

import cucumber.api.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class JsonToCollections {

    @Test
    public void hrAPIEmployee_JsonData_To_Java_Object(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("employee_id", 105)
                .when().get("http://34.201.69.55:1000/ords/hr/employees/{employee_id}");

        response.prettyPrint();
        Map<String, Object> employeeMap = response.body().as(Map.class);
        System.out.println("employeeMap = " + employeeMap.toString());

        String firstName = employeeMap.get("first_name").toString();
        System.out.println("firstName = " + firstName);
        assertEquals("David", firstName);

        int salary = (int) employeeMap.get("salary");
        System.out.println("salary = " + salary);
        assertEquals(4800, salary);
//        double salary = (Double)employeeMap.get("salary"); ==> IT'S ANOTHER WAY BUT DIDN'T WORK FOR ME
//        System.out.println("salary = " + salary);
//        assertEquals(4800.5, salary,0.5);
//        //delta amount. if there Difference by delta amount or less,
//        // values will be considered equalTo

    }

    //List<Map<String, Object>>
    @Test
    public void convertAllSpartans_to_list_of_map(){

        Response response = given().accept(ContentType.JSON)
                .when().get("http://34.201.69.55:8000/api/spartans");
           //     .when().get("http://34.201.69.55:1000/ords/hr/employees");
        List<Map<String, ?>> jsonListOfMap = response.as(List.class);
        System.out.println(jsonListOfMap);
        //print the data of first spartan
        System.out.println(jsonListOfMap.get(0));

        Map<String, ?> first = jsonListOfMap.get(0);
        System.out.println("first = " + first);
        System.out.println("first = " + first.get("name"));

        int counter = 1;
        for (Map<String, ?> spartan : jsonListOfMap){
            System.out.println(counter+ " spartan = " + spartan);
            counter++;
        }

        for (Map<String, ?> idSpartan : jsonListOfMap){
            System.out.println("idSpartan.get(\"id\") = " + idSpartan.get("id")+" "+ idSpartan.get("name"));
        }

    }

    @Test
    public void regions_data_to_colections(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://34.201.69.55:1000/ords/hr/regions/");
// our response variable in json format, we convert it to Map(key-value) structure
        Map<String, ?> dataMap = response.body().as(Map.class);
        System.out.println("dataMap = " + dataMap);
        System.out.println("dataMap = " + dataMap.get(0)); // null
        System.out.println("dataMap = " + dataMap.get("region_name"));
        System.out.println(dataMap.get("items")); // this returns us List of Map

        List<Map<String , ?>> regionList = (List<Map<String, ?>>) dataMap.get("items");
        System.out.println("regionMap = " + regionList);
        System.out.println("mapEurope = " + regionList.get(0).get("region_name"));
        System.out.println("map = " + regionList.get(1).get("region_name"));
        System.out.println("map = " + regionList.get(2).get("region_name"));
    }


}
