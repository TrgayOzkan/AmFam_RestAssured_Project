import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class TestWithQueryParam {


    @BeforeAll
    public static void setupClass(){
        baseURI="https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @Test
    public void queryParam1(){

        Response response = given().queryParam("page", 2)
                .queryParam("limit", 2)
                .queryParam("state", "created")
                .when().get("/shop/orders/");

        System.out.println(response.prettyPeek());
        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        assertTrue(response.asString().contains("created"));


    }

    @Test
    public void queryParam2(){
        //creating map for query Params

        Map<String,Object> paramsMap = new HashMap<>();

        paramsMap.put("page",2);
        paramsMap.put("limit",2);
        paramsMap.put("state","created");

        //send request

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/shop/orders/");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json; charset=utf-8");

        assertTrue(response.asString().contains("created"));
    }

}
