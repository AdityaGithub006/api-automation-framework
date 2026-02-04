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


public class CreatePetTest extends BaseTest {
    private final PetClient petClient = new PetClient();
    @Test
    public void createPetShouldReturn_200andMatchIdName(){
        Pet pet = PetFactory.randomAvailablePet();
        Response res = petClient.createPet(pet);
        res.then()
                .spec(BaseApiClient.res200Json())
                .log().all();
        Number actualId = res.then().extract().path("id");
        String actualName = res.then().extract().path("name");

        TestContext.setPetId(actualId.longValue());

        Assert.assertEquals(actualId.longValue(), (long)pet.id, "Pet Id Matched");
        Assert.assertEquals(actualName, pet.name, "Pet Name Matched");

        res.then()
                .spec(BaseApiClient.res200JsonWithSchema("schema/pet/create_pet_200.json"));
    }
    @Test
    public void getPetShouldReturn_404whenPetNotFound(){
        petClient.getPetById(9999999L)
                .then()
                .spec(BaseApiClient.res404());
    }
}
