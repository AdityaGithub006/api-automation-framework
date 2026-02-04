package com.aditya.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties PROPS = new Properties();

    static {
        String env = System.getProperty("env", "local"); // default local
        String path = "env/" + env + ".properties";

        try (InputStream is = TestConfig.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Config not found: " + path);
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }

    private TestConfig() {}

    public static String baseUrl() {
        return PROPS.getProperty("baseUrl");
    }
    public static String basePath(){
        return PROPS.getProperty("basePath", "");
    }
}