package filters;

import common.TestApis;
import io.restassured.filter.log.ErrorLoggingFilter;

import static io.restassured.RestAssured.given;

/**
 * ErrorLoggingFilter — logs response when status is 4xx/5xx (extends StatusCodeBasedLoggingFilter).
 */
public class ErrorLoggingFilterDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .filter(new ErrorLoggingFilter())
        .get("/status/500")
        .then()
        .statusCode(500);

    System.out.println("ErrorLoggingFilterDemo — check console for error log output.");
  }
}
