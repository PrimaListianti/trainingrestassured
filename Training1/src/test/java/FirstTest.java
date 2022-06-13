import Share.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstTest extends BaseTest {

    @Test
    public void first()
    {
        //RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = given();
        request.param("page", 1);

        Response response = request.get("api/users");

        response.then().assertThat().statusCode(200);

        System.out.println(response.asString());

        String page = response.jsonPath().getString("page");
        System.out.println("page: "+ page);
        String email = response.jsonPath().getString("data[0].first_name");
        System.out.println("first_name: "+email);
    }


    }



