package com.Test.Sankari;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.FilterableRequestSpecification;

public class reqspec {
    private static config testConfig = config.getInstance("https://restful-booker.herokuapp.com");
    private static String baseURI = testConfig.getBaseURI();

    public static FilterableRequestSpecification postBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification getBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification postAuthSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/auth").
                build();
    }

    public static FilterableRequestSpecification putBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }

    public static FilterableRequestSpecification deleteBookingSpec() {
        return (FilterableRequestSpecification) new RequestSpecBuilder().
                setBaseUri(baseURI + "/booking").
                build();
    }
}
