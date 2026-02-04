package com.aditya.api.client;

import com.aditya.api.config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class BaseApiClient {
    private BaseApiClient(){
    }
    public static RequestSpecification reqJson(){
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setBasePath(TestConfig.basePath())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
    public static ResponseSpecification res200Json(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification res400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
    public static ResponseSpecification res404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }
    public RequestSpecification reqJsonWithAuth(String token){
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.baseUrl())
                .setBasePath(TestConfig.basePath())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer"+token)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
    public static ResponseSpecification res200JsonWithSchema(String classPathSchema){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody(matchesJsonSchemaInClasspath(classPathSchema))
                .build();
    }
}
