package com.Test.Sankari;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static com.Test.Sankari.reqspec.postBookingSpec;

public class bookerClient implements apirestfulClient {

    @Override
    public Response createBooking(booking postBookingBody, bookingHeaders postBookingheaders) {
        return given().
                    spec(postBookingSpec()).
                    body(postBookingBody).
                    header(postBookingheaders.getContentTypeHeader()).
                    header(postBookingheaders.getAcceptHeader()).
               when().
                    post();
    }

    @Override
    public Response getBooking(bookingHeaders getBookingHeaders, String bookingId) {
        return given().
                    spec(reqspec.getBookingSpec()).
                    pathParam("id", bookingId).
                    body(booking.setPostBookingBody()).
                    header(getBookingHeaders.getAcceptHeader()).
               when().
                    get("/{id}");
    }
    @Override
    public Response updateBooking(bookingHeaders updateBookingHeaders, fullBooking fullBooking) {

        return given().
                spec(reqspec.putBookingSpec()).
                header(updateBookingHeaders.getContentTypeHeader()).
                header(updateBookingHeaders.getAcceptHeader()).
                header(updateBookingHeaders.getCookieHeader()).
                pathParam("id", fullBooking.getBookingid()).
                body(fullBooking.getBooking()).
                when().
                put("/{id}");
    }
    @Override
    public Response deleteBooking(bookingHeaders deleteBookingHeaders, Number bookingId) {
        return given().
                    spec(reqspec.deleteBookingSpec()).
                    pathParam("id", bookingId).
                    header(deleteBookingHeaders.getContentTypeHeader()).
                    header(deleteBookingHeaders.getCookieHeader()).
               when().
                    delete("/{id}");
    }

    //@Override
    public Response createAuthToken(bookingHeaders authHeaders, String userName, String pwd) {
        return given().
                spec(reqspec.postAuthSpec()).
                body(booking.setPostAuthBody(userName, pwd)).
                header(authHeaders.getContentTypeHeader()).
                when().
                post();
    }


}
