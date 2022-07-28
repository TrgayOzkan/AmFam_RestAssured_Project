import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class TestDeSerialization {

    @BeforeAll
    public static void setupClass(){

        baseURI = "https://api.predic8.de:443";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @Test
    public void testDeSer1(){

        Response res = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/shop/products/{id}");

        res.prettyPeek();
        Map <String,Object> jsonMap = res.body().as(Map.class);

        System.out.println("name = " + jsonMap.get("name"));
        System.out.println("price = " + jsonMap.get("price"));
        System.out.println("photo_url = " + jsonMap.get("photo_url"));
        System.out.println(jsonMap.entrySet());
        Assertions.assertTrue(jsonMap.entrySet().toString().contains("Exotic"));

    }
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                            .when().get("/shop/customers/");

        //response.prettyPeek();
        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getString(\"customers\") = " + jsonPath.getString("customers"));

        List<Map<String,Object>> listOfCustomers = response.path("customers");

        System.out.println("listOfCustomers = " + listOfCustomers);

        System.out.println(listOfCustomers.get(0));

        Map<String,Object> firstCustomer = listOfCustomers.get(0);

        System.out.println("firstname = " + firstCustomer.get("firstname"));

        int counter =1;
        for (Map<String, Object> map : listOfCustomers) {
            System.out.println(counter + " - customer "+ map);
            counter++;
        }

    }


}
