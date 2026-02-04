package com.aditya.api.base;

public final class TestContext {
    private static final ThreadLocal<Long> petId = new ThreadLocal<>();
    private TestContext(){}
    public static void setPetId(long id){
        petId.set(id);
    }
    public static Long getPetId(){
        return petId.get();
    }
    public static void clear(){
        petId.remove();
    }
}
