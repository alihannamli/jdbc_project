package Day6restapi_gson_serialization;

import com.google.gson.Gson;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class POJO_deserialization {

    @Test
    public void spartan_to_pojo_object_deserialization() {
    Response response = given().accept(ContentType.JSON)
            .when().get("http://3.95.173.92:8000/api/spartans/15");
//        System.out.println(response.statusCode());
//        System.out.println(response.headers());

        response.prettyPrint();

//        //deserialize json to pojo java object.
//        //JSON response body >>> Custom Java class object
    // taking the body of the response and converting to object
    Spartan spartan = response.body().as(Spartan.class);

    System.out.println(spartan.getName());
    System.out.println(spartan.getGender());
    System.out.println(spartan.getSpartanID());
    System.out.println(spartan.getPhone());

    System.out.println("spartan.toString() = " + spartan.toString());
//
    assertEquals("Meta", spartan.getName());
    assertEquals("Female", spartan.getGender());
    assertEquals(new Integer(15), spartan.getSpartanID());
    assertEquals(new Long(1938695106), spartan.getPhone());

}
}
