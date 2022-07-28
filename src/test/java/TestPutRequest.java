import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPutRequest {

    @BeforeAll
    public static void setup(){
        baseURI = "https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @Test
    public void PutRequest(){

        Map<String,Object> putMap = new HashMap<>();
        putMap.put("firstname","Miky");
        putMap.put("lastname", "Chuky");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id",367)
                .body(putMap)
                .when().put("/shop/customers/{id}")
                .then().assertThat().statusCode(200);

    }
    @Test
    public void DeleteRequest(){

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id",367)
                .when().delete("/shop/customers/{id}")
                .then().assertThat().statusCode(200);

    }

}
