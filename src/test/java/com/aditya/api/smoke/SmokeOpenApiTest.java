package com.aditya.api.smoke;

import com.aditya.api.config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SmokeOpenApiTest {

    @Test
    public void shouldLoadOpenApiSpec_200() {
        given()
                .baseUri(TestConfig.baseUrl())
        .when()
                .get("/api/v3/openapi.json")
        .then()
                .statusCode(200);
    }
}