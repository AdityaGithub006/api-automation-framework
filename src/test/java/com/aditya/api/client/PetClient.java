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
                .post("/pet");
    }
    public Response getPetById(long id){
        return given()
                .spec(BaseApiClient.reqJson())
        .when()
                .get("/pet/{id}", id);
    }
    public Response deletePet(long id){
        return given()
                .spec(BaseApiClient.reqJson())
        .when()
                .delete("/pet/{id}", id);
    }
}
