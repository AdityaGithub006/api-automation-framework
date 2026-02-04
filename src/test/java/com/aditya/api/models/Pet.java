package com.aditya.api.models;

import java.util.List;

public class Pet {
    public long id;
    public String name;
    public List<String> photoUrls;
    public String status;
    public Pet(long id, String name, List<String> photoUrls, String status){
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.status = status;
    }
}
