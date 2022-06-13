package users;

import Share.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListUserTest extends BaseTest {
    @Test
    public void first(ITestContext context)
    {
        RequestSpecification request = given();
        request.param("page", 1);

        Response response = request.get("api/users");

        response.then().assertThat().statusCode(200);

        context.setAttribute("id_user", response.jsonPath().getInt("data[0].id"));
        //System.out.println(context.getAttribute("id_user").toString());

        //System.out.println(response.asString());
    }
}

