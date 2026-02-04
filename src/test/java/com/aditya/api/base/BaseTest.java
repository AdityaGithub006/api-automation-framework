package com.aditya.api.base;

import com.aditya.api.client.PetClient;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    private final PetClient petClient = new PetClient();
    @AfterMethod (alwaysRun = true)
    public void cleanUp(ITestResult result){
        Object petIdObject = result.getAttribute("petId");

        if(petIdObject == null) return;

        long petId = ((Number) petIdObject).longValue();

        try {
            petClient.deletePet(petId); // best-effort cleanup
        }
        catch (Exception ignored){}
    }
}
