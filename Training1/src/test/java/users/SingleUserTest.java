package users;

import Share.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.ResultSet;

import static io.restassured.RestAssured.given;

public class SingleUserTest extends BaseTest {
    @Test
    public void first(ITestContext context)
    {
        RequestSpecification request = given();
        request.param("page", 1);

        Response response = request.get("api/users/"+context.getAttribute("id_user").toString());

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resource/schema/users/SingleUser.json")));

        System.out.println(response.asString());
    }
}
