package com.Test.Sankari;

import io.restassured.http.Header;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class bookingHeaders {

    private Header contentTypeHeader;
    private Header acceptHeader;
    private Header cookieHeader;
    private Header authorisationHeader;

    public static bookingHeaders setPostBookingHeaders() {
        return  bookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                acceptHeader(new Header("Accept", "application/json")).
                build();
    }

    public static bookingHeaders setGetBookingHeaders() {
        return  bookingHeaders.builder().
                acceptHeader(new Header("Accept", "application/json")).
                build();
    }

    public static bookingHeaders setPostAuthHeaders() {
        return  bookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                build();
    }

    public static bookingHeaders setPutBookingHeaders(String token) {
        return  bookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                acceptHeader(new Header("Accept", "application/json")).
                cookieHeader(new Header("Cookie", "token="+token)).
                build();
    }

    public static bookingHeaders setDeleteBookingHeaders(String token) {
        return  bookingHeaders.builder().
                contentTypeHeader(new Header("Content-Type","application/json")).
                cookieHeader(new Header("Cookie", "token="+token)).
                build();
    }
}
