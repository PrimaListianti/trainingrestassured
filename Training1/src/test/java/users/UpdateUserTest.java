package users;

import Share.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseTest {
    @Test
    public void updateuser() {
        updateuser("morpheus", "zion resident");
    }
    public void updateuser (String name, String job) {
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        // add JSOn object to body
        request.body(params.toString());

        // set content type
        request.header("Content-Type", "application/json");
        Response response = request.post("/api/users/2");
        response.then().assertThat()
                .statusCode(201)
                .body("name", Is.is("morpheus"))
                .body("job", Is.is("zion resident"))
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/users/UpdateUser.json")));
        System.out.println(response.asString());
    }
}
