package com.aditya.api.client;

import com.aditya.api.config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class BaseApiClient {
    private BaseApiClient(){
    }
    public static RequestSpecification reqJson(){
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setContentType("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
    public static ResponseSpecification res200Json(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(JSON)
                .build();
    }
    public static ResponseSpecification res400Json(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
    public static ResponseSpecification res404Json(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

}
