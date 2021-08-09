package com.Test.Sankari;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import lombok.Getter;

import static io.restassured.mapper.ObjectMapperType.GSON;

public class config {

    private static config INSTANCE = null;
    @Getter
    private String baseURI = null;

    private config(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.config = RestAssuredConfig.config().
                objectMapperConfig(new ObjectMapperConfig(GSON)).
                logConfig(new LogConfig().
                        enableLoggingOfRequestAndResponseIfValidationFails());
    }

    public static config getInstance(String baseURI) {
        if (INSTANCE == null) {
            synchronized (config.class) {
                if (INSTANCE == null) {
                    INSTANCE = new config(baseURI);
                }
            }
        }
        return INSTANCE;
    }

}
