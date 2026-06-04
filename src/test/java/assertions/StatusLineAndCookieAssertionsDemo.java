package assertions;

import common.TestApis;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * then() validations — statusLine, cookies, time, contentType, detailedCookies.
 */
public class StatusLineAndCookieAssertionsDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .get("/cookies/set?sid=abc")
        .then()
        .statusCode(200)
        .statusLine(containsString("200"))
        .time(lessThan(10L), TimeUnit.SECONDS)
        .cookie("sid", equalTo("abc"));

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .contentType(containsString("application/json"));

    System.out.println("StatusLineAndCookieAssertionsDemo — done.");
  }
}
