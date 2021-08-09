package com.Test.Sankari;

import io.restassured.response.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class booking {
    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private bookingDates bookingdates;
    private String additionalneeds;
    private String username;
    private String password;

    public static booking setPostBookingBody() {
        return booking.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(bookingDates
                        .dfltBookingReqBody().get())
                .additionalneeds("Breakfast")
                .build();
    }

    public static booking setPostAuthBody(String username, String password) {
        return booking.builder()
                .username(username)
                .password(password)
                .build();
    }

    public static String createAuthToken(bookerClient bookerClient) {
        String userName = "admin";
        String pwd = "password123";
        Response authToken = bookerClient.createAuthToken(bookingHeaders.
                                                            setPostAuthHeaders(),
                                                          userName, pwd);
        return authToken.jsonPath().get("token");
    }
}
