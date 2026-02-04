package com.aditya.api.test.Pet;


import com.aditya.api.client.BaseApiClient;
import com.aditya.api.client.PetClient;
import com.aditya.api.models.Pet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CreatePetTest {
    private final PetClient petClient = new PetClient();
    @Test
    public void createPetShouldReturn_200andMatchIdName(){
        long id = ThreadLocalRandom.current().nextInt(100000, 999999);
        String name = "HB-"+id;

        Pet pet = new Pet(id, name, List.of("x"), "available");
        Response res = petClient.createPet(pet);
        res.then()
                .spec(BaseApiClient.res200Json())
                .log().all();
        Number actualId = res.then().extract().path("id");
        String actualName = res.then().extract().path("name");

        Assert.assertEquals(actualId.longValue(), (long)id, "Pet Id Matched");
        Assert.assertEquals(actualName, name, "Pet Name Matched");
    }
    @Test
    public void getPetShouldReturn_404whenPetNotFound(){
        petClient.getPetById(9999999L)
                .then()
                .spec(BaseApiClient.res404Json());
    }
}
