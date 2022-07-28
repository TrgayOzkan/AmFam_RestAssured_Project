import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.Math.round;


public class TestWithJsonPath {

    @BeforeAll
    public static void setupClass(){
        baseURI = "https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 33)
                .when().get("/shop/products/{id}");

        response.prettyPeek();

        assertEquals(response.statusCode(),200);

        float price = response.path("price");
        System.out.println("price = " + price);

        JsonPath jp = response.jsonPath();

        String name = jp.getString("name");
        float price1 = jp.getFloat("price");

        System.out.println("name = " + name);
        System.out.println("price1 = " + price1);

        assertEquals(name,"Pineapples");
        assertEquals(price1,round(3.549999952316284),2);


    }


}
