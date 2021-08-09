import io.restassured.response.Response;
import org.hamcrest.core.*;
import org.junit.Test;
import com.Test.Sankari.bookerClient;
import com.Test.Sankari.booking;
import com.Test.Sankari.bookingHeaders;
import com.Test.Sankari.fullBooking;
import static org.hamcrest.MatcherAssert.assertThat;


public class apiAutRestful {
    @Test
    public void createBooking() {
        booking postBookingBody = booking.setPostBookingBody();

        bookingHeaders postBookingHeaders = bookingHeaders.setPostBookingHeaders();

        bookerClient bookerClient = new bookerClient();
        //create new booking
        Response booking = bookerClient.createBooking(postBookingBody, postBookingHeaders);

        String bookingId = booking.getBody().jsonPath().getString("bookingid");

        //Validating and Printing the response details
        assertThat(booking.statusCode(), IsEqual.equalTo(200));

        if(booking.statusCode() == 200) {
            System.out.println("---------------------------Create Booking----------------------:");

            System.out.println("Status Code:" + booking.statusCode());

            System.out.println("Status Line:" + booking.statusLine());

            System.out.println("Booking ID:" + bookingId);

            System.out.println("New created Booking details : " + booking.body().asString());

            System.out.println("Booking Created Successfully:" + booking.getBody().jsonPath().getString("booking").contains("Jim"));

            System.out.println("-----------------------------------------------------------------:");
        }else{
            System.out.println("Status Code:" + booking.statusCode());

            System.out.println("Booking not created : " + booking.body().asString());
        }
    }

    @Test
    public void updateBooking() {
        bookerClient bookerClient = new bookerClient();

        String token = booking.createAuthToken(bookerClient);

        fullBooking fullBooking = bookerClient.createBooking(
                booking.setPostBookingBody(),
                bookingHeaders.setPostBookingHeaders()).as(com.Test.Sankari.fullBooking.class);

        //Updating the booking details value
        fullBooking.getBooking().setLastname("william");
        fullBooking.getBooking().setTotalprice(222);

        bookingHeaders updateBookingHeaders = bookingHeaders.setPutBookingHeaders(token);

        Response putBookingResponse = bookerClient.updateBooking(updateBookingHeaders, fullBooking);

        //Validating and Printing the response details
        System.out.println("------------------------Update Booking---------------------------:");

        if(putBookingResponse.statusCode()==200){
            putBookingResponse.
                    then().
                    assertThat().
                    statusCode(200);
            System.out.println("Status Code:" + putBookingResponse.statusCode());
            System.out.println("Status Line:" + putBookingResponse.statusLine());
            System.out.println("Last name : "+fullBooking.getBooking().getLastname()+" is updated successfully:" + fullBooking.getBooking().getLastname().equals("william"));
            System.out.println("Totalprice : "+ fullBooking.getBooking().getTotalprice()+" is value updated successfully:" +  fullBooking.getBooking().getTotalprice().equals(222));
            System.out.println("Updated Booking Details :"+putBookingResponse.body().asString());
            System.out.println("Booking details Updated Successfully");

        }else{

            System.out.println("Booking details not updated and recieved status code us :" + putBookingResponse.statusCode());
        }
        System.out.println("--------------------------------------------------------------------:");

    }

   @Test
    public void deleteBooking() {
        bookerClient bookerClient = new bookerClient();

        String token = booking.createAuthToken(bookerClient);

        fullBooking fullBooking = bookerClient.createBooking(
                        booking.setPostBookingBody(),
                        bookingHeaders.setPostBookingHeaders())
                .as(com.Test.Sankari.fullBooking.class);
       System.out.println("------------------------Delete Booking----------------------------:");

       System.out.println("Booking ID :"+fullBooking.getBookingid());

        bookingHeaders deleteBookingHeaders =
                bookingHeaders.setDeleteBookingHeaders(token);

        // delete the booking
       Response delresponse= bookerClient.deleteBooking(deleteBookingHeaders,
                fullBooking.getBookingid());

       delresponse.then().assertThat().statusCode(201);

       System.out.println("Status Code:" + delresponse.statusCode());

       if(delresponse.statusCode()==201){
           System.out.println("Booking record is deleted and status code is :" + delresponse.statusCode());
           Response getBookingResponse = bookerClient.
                   getBooking(bookingHeaders.setGetBookingHeaders(),
                           fullBooking.getBookingid().toString());

           // Checking whether booking is deleted
           getBookingResponse.
                   then().
                   assertThat().statusCode(404);
           if(getBookingResponse.statusCode()==404){
               System.out.println("Deleted booking does not exist and status code is :" + getBookingResponse.statusCode());

           }else{
               System.out.println("Booking record not deleted and status code is :" + delresponse.statusCode());

           }
       }
       System.out.println("----------------------------------------------------------------------------");

   }
   //@Test
    public void getBooking() {
        bookingHeaders getBookingHeaders = bookingHeaders.setGetBookingHeaders();

        bookerClient bookerClient = new bookerClient();
        Response getBookingResponse = bookerClient.getBooking(getBookingHeaders, "23");

        getBookingResponse.
        then().
            assertThat().statusCode(200);
       System.out.println("The response status is "+getBookingResponse.print());

    }

   // @Test
    public void postAuth() {
        bookingHeaders authHeaders = bookingHeaders.setPostAuthHeaders();

        String userName = "admin";
        String pwd = "password123";

        bookerClient bookerClient = new bookerClient();
        Response authToken = bookerClient.createAuthToken(authHeaders, userName, pwd);
        String body = authToken.body().asString();
        System.out.println(body);
        assertThat(authToken.statusCode(), IsEqual.equalTo(200));
    }

}
