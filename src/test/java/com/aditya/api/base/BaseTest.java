package com.aditya.api.base;

import com.aditya.api.client.PetClient;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    private final PetClient petClient = new PetClient();
    @AfterMethod (alwaysRun = true)
    public void cleanUp(){
        Long id = TestContext.getPetId();
        if(id != null){
            petClient.deletePet(id);
        }
        TestContext.clear();
    }
}
