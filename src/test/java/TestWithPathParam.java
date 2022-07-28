import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class TestWithPathParam {

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
                .and().pathParam("id", 33)
                .when().get("/shop/products/{id}");

        System.out.println(response.prettyPeek());

        assertEquals(response.statusCode(),200);

        //printing values of json keys
        System.out.println("name: " + response.body().path("name").toString());
        System.out.println("price: " + response.path("price").toString());

        String name = response.body().path("name");
        float price = response.path("price");

        System.out.println("name = " + name);
        System.out.println("price = " + price);

        assertEquals(name,"Pineapples");
        assertEquals(price,3.55f);

    }

    @Test
    public void test2(){
        Response response = get("/shop/products/");

      System.out.println(response.prettyPeek());

        String firstName = response.path("products.name[0]");
        System.out.println("name = " + firstName);

        List<String> names = response.path("products.name");
        System.out.println("names = " + names);

        System.out.println("names.size() = " + names.size());

        for (String name : names){
            System.out.println(name);
        }


    }


}
