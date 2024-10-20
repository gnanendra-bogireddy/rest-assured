package assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;

public class AssertionsDemo {
    public static void main(String[] args) {

        // Rest assured has default assertions.
        // If you want to validate cookies use the code below.
        RestAssured.get("/x").then().assertThat().cookie("cookieName", "cookieValue");
        RestAssured.get("/x").then().assertThat().cookies("cookieName1", "cookieValue1", "cookieName2", "cookieValue2");

        // If you want to validate headers use the code below.
        RestAssured.get("/x").then().assertThat().header("headerName", "headerValue");
        RestAssured.get("/x").then().assertThat().headers("headerName1", "headerValue1", "headerName2", "headerValue2");

        // If you want to perform status code validations use the code below.
        // containsString("something") is from hamcrest library.
        RestAssured.get("/x").then().assertThat().statusCode(200);
        RestAssured.get("/x").then().assertThat().statusLine("something");
        RestAssured.get("/x").then().assertThat().statusLine(containsString("some"));

        // If you want to perform content type validations use the code below.
        RestAssured.get("/x").then().assertThat().contentType(ContentType.JSON);

        // If you want to perform body validations use the code below.
        RestAssured.get("/x").then().assertThat().body(equalTo("something"));

        long timeInMs = RestAssured.get("/lotto").time();
        // or using a specific time unit:

        long timeInSeconds = RestAssured.get("/lotto").timeIn(SECONDS);
        // where SECONDS is just a standard TimeUnit. You can also validate it using the validation DSL:

        RestAssured.get("/lotto").then().
                time(lessThan(2000L)); // Milliseconds in time.
    }
}
