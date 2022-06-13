package Register;

import Share.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RegisterUnsucessfull extends BaseTest {
    @Test
    public void registerdataunsucess(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "sydney@fife");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/register");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/register/RegisterUnsuccess.json")));
        System.out.println(response.asString());
    }

}
