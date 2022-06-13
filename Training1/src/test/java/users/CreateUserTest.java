package users;

import Share.BaseTest;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseTest {
    @Test

    public void sendPOSTData()
    {
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("name", "John Tere");
        params.put("job", "leader");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/users");
        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/users/CreateUser.json")));
        System.out.println(response.asString());
    }



}
