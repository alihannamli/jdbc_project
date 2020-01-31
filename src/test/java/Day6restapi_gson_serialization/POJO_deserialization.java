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
    public void spartan_to_pojo_object_deserialization(){
        Response response = given().accept(ContentType.JSON)
                    .when().get("http://34.201.69.55:1000/ords/hr/regions/9");
         response.prettyPrint();
        Spartan spartan = response.body().as(Spartan.class);
//        System.out.println(spartan.getRegion_id());
//        System.out.println(spartan.getRegion_name());

    }
}
