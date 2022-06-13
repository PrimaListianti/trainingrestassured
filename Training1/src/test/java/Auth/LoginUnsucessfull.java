package Auth;

import Share.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class LoginUnsucessfull extends BaseTest {
    @Test
    public void loginunsuccess(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "peter@klaven");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/login");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/auth/LoginUnsuccess.json")));
        System.out.println(response.asString());
    }

}
