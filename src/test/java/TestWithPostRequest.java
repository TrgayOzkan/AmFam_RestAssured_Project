import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestWithPostRequest extends TestBase{


   /* @BeforeAll
    public static void setup(){
        baseURI = "https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    */

    @Test
    public void PostWithString(){

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"firstname\": \"Trgy\",\n" +
                        "  \"lastname\": \"Okaan\"\n" +
                        "}")
                .when().post("/shop/customers/");

        response.prettyPeek();
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath jp = response.jsonPath();
        assertEquals(jp.getString("lastname"),"Okaan");


    }

    @Test
    public void PostWithMap(){

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("firstname","Mikey");
        requestMap.put("lastname", "Chuky");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/shop/customers/");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        response.prettyPeek();

    }

    @Test
    public void PostWithPojo(){

        Product_POJO prod = new Product_POJO();
        prod.setName("Dates");
        prod.setPrice(4.32f);
        prod.setCategory_url("/shop/categories/Fruits");
        prod.setVendor_url("/shop/vendors/672");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(prod)
                .when().post("/shop/products/");

        response.prettyPeek();
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json; charset=utf-8");




    }


}
