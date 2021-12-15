package W;

import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RestfullBookerTest {



    @Test
    void getToken() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\CreateToken.json"));
        String createTokenBody="";
        String lineReader="";
        while((lineReader = reader.readLine()) != null)
        {
            createTokenBody+=lineReader;
        }
        given()
                .headers("Content-Type","application/json")
                .body(createTokenBody.getBytes(StandardCharsets.UTF_8))
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200);

    }

    @Test
    void getBookingIds() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\CreateBooking.json"));
        String createBookingBody="";
        String lineReader="";
        while((lineReader = reader.readLine()) != null)
        {
            createBookingBody+=lineReader;
        }
        System.out.println(createBookingBody);
        given()
                .headers("Content-Type","application/json")
                .body(createBookingBody.getBytes(StandardCharsets.UTF_8))
                .expect()
                .body("booking.firstname",is("John"))
                .body("booking.lastname",is("Williams"))
                .body("booking.totalprice",is(121))
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200);


    }

    @Test
    void getBooking() {
    }

    @Test
    void createBooking() {
    }

    @Test
    void updateBooking() {
    }

    @Test
    void partialUpdateBooking() {
    }

    @Test
    void deleteBooking() {
    }
}