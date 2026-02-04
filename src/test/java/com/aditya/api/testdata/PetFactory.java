package com.aditya.api.testdata;

import com.aditya.api.models.Pet;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PetFactory {
    private PetFactory(){}
    public static Pet randomAvailablePet(){
        long id = ThreadLocalRandom.current().nextLong(100000, 999999);
        String name = "HB-"+id;
        return new Pet(id, name,List.of("x"),"available");
    }
    public static Pet pet(long id, String name, String status){
        return new Pet(id, name, List.of("x"), status);
    }
}
