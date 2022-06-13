import Share.BaseTest;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SecondTest extends BaseTest {

    @Test(dataProvider = "data-users")

    public void second(String name, String job) {
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        // add JSOn object to body
        request.body(params.toString());

        // set content type
        request.header("Content-Type", "application/json");
        Response response = request.post("/api/users");
        response.then().assertThat()
                .statusCode(201)
                .body("name", Is.is("Prima"))
                .body("job", Is.is("QA Engineer"))
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/users/CreateUser.json")));
        System.out.println(response.asString());

    }

    @DataProvider(name = "data-users")
    Object[][] DataUsers() {
        Object[][] users = new Object[][]{
                {"John", "Backend"},
                {"Doe", "Frontend"},
                {"Tere", "QA"}
        };

        return users;
    }

}
