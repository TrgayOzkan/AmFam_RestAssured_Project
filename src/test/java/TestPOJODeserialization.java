import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.Math.round;


public class TestPOJODeserialization extends TestBase{

   /*
    @BeforeAll
    public static void setup(){
        baseURI = "https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/shop/products/{id}");

        response.prettyPeek();

        Product_POJO prod = response.body().as(Product_POJO.class);

        System.out.println(prod.toString());
        assertEquals(prod.getName(),"Lychee");
        assertEquals(prod.getPrice(),round(3.9),1);


    }


}
