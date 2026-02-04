package com.aditya.api.client;

import com.aditya.api.models.Pet;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class PetClient {
    public Response createPet(Pet pet){
        return given()
                .spec(BaseApiClient.reqJson())
                .body(pet)
        .when()
                .post("/api/v3/pet");
    }
    public Response getPetById(long id){
        return given()
                .spec(BaseApiClient.reqJson())
        .when()
                .get("/api/v3/pet/{id}", id);
    }
    public Response deletePet(long id){
        return given()
                .spec(BaseApiClient.reqJson())
        .when()
                .delete("/api/v3/pet/{id}", id);
    }
}
