package com.Test.Sankari;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Getter
@Setter
@Builder
public class bookingDates {
   private String checkin;
    private String checkout;

    public static Supplier<bookingDates> dfltBookingReqBody() {
        return () -> bookingDates.builder().
                checkin("2030-01-01").
                checkout("2030-01-02").
                build();
    }
}
