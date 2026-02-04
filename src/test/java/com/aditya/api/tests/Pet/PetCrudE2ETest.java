package com.aditya.api.tests.Pet;

import com.aditya.api.base.BaseTest;
import com.aditya.api.base.TestContext;
import com.aditya.api.client.BaseApiClient;
import com.aditya.api.client.PetClient;
import com.aditya.api.models.Pet;
import com.aditya.api.testdata.PetFactory;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetCrudE2ETest extends BaseTest {
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

        // Register for automatic cleanup (@AfterMethod)
        TestContext.setPetId(createdId);

        // 2) Get
        petClient.getPetById(createdId)
                .then()
                .spec(BaseApiClient.res200Json());

        // No explicit delete here — BaseTest.cleanup() will delete after test
    }
}
