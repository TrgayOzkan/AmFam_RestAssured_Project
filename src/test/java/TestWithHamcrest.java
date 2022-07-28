import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestWithHamcrest {

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

        given().accept(ContentType.JSON)
                .pathParam("id",33)
        .when().get("/shop/products/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8");

    }
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .pathParam("id",33)
                .when().get("/shop/products/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat().body("name", equalTo("Pineapples"),
                                        "price",equalTo(3.55f));


    }

}
