package com.aditya.api.tests.Pet;

import com.aditya.api.client.BaseApiClient;
import com.aditya.api.client.PetClient;
import com.aditya.api.models.Pet;
import com.aditya.api.testdata.PetFactory;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetCrudE2ETest {
    private final PetClient petClient = new PetClient();

    @Test
    public void crudTest(){

        // 1) Create
        Pet pet = PetFactory.randomAvailablePet();
        Response createdRes = petClient.createPet(pet);

        createdRes.then().spec(BaseApiClient.res200Json());

        Number createdNum = createdRes.then().extract().path("id");
        long createdId = createdNum.longValue();

        Assert.assertEquals(createdId, pet.id, "Created Pet Id mismatch");

        // 2) Get
        petClient.getPetById(createdId)
                .then()
                .spec(BaseApiClient.res200Json());

        // 3) Delete
        petClient.deletePet(createdId)
                .then()
                .spec(BaseApiClient.res200Json());

        // 4) Verify Delete
        petClient.getPetById(createdId)
                .then()
                .spec(BaseApiClient.res404());
    }
}
