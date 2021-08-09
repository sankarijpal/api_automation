package com.Test.Sankari;

import io.restassured.response.Response;


public interface apirestfulClient {
    Response createBooking(booking createBookingBody, bookingHeaders createBookingHeaders);

    Response deleteBooking(bookingHeaders bookingHeaders, Number bookingId);

    Response getBooking(bookingHeaders getBookingHeaders, String bookingId);

    Response createAuthToken(bookingHeaders authHeaders, String userName, String pwd);

    Response updateBooking(bookingHeaders updateBookingHeaders, fullBooking fullBooking);
}

