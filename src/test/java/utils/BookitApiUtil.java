package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BookitApiUtil {

    private static Response response;

    public static void setResponse(Response response) {
        BookitApiUtil.response = response;
    }

    public static Response getResponse() {
        return response;
    }

    public static String generateToken(){

        Response response = given().queryParams("email",ConfigurationReader.getProperty("bookit.email"),
                                                "password",ConfigurationReader.getProperty("bookit.password"))
                .when().get(ConfigurationReader.getProperty("bookit.baseuri")+"/sign");
                                                                    // sign will return us a token for this email and password
//        String token = response.body().jsonPath().getString("accessToken");

        JsonPath jsonPath = response.body().jsonPath();
        String token1 = jsonPath.getString("accessToken");

        return token1;

    }
}
