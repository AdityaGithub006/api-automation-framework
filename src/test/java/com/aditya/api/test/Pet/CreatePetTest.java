package com.aditya.api.test.Pet;

import com.aditya.api.client.BaseApiClient;
import com.aditya.api.config.TestConfig;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatePetTest {
    @Test
    public void createPetShouldReturn_200andMatchIdName(){
        int id = ThreadLocalRandom.current().nextInt(100000, 999999);
        String name = "HB-"+id;

        Map<String, Object> body = Map.of(
                "id", id,
                "name", name,
                "photoUrls", new String[]{"x"},
                "status", "available"
        );

        given()
                .spec(BaseApiClient.reqJson())
                .body(body)
        .when()
                .post("/api/v3/pet")
        .then()
                .spec(BaseApiClient.res200Json())
                .body("id", equalTo(id))
                .body("name", equalTo(name));
    }
}
