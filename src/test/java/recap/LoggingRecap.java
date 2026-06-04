package recap;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import static io.restassured.RestAssured.given;

/**
 * Logging — debug failed tests and trace traffic.
 * <p>
 * Request: {@code .log().all() | .headers() | .body() | .uri() | .method() | .params()}
 * Response in then(): {@code .log().all() | .body() | .ifError() | .ifValidationFails()}
 * Filters: {@link RequestLoggingFilter}, {@link ResponseLoggingFilter}, {@link ErrorLoggingFilter}
 * Config: {@code LogConfig.enableLoggingOfRequestAndResponseIfValidationFails()}
 */
public class LoggingRecap {

  public static void main(String[] args) {
    System.out.println("--- Request logging ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .log().all()
        .get("/posts/1")
        .then()
        .log().ifValidationFails()
        .statusCode(200);

    System.out.println("--- Filter-based logging ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter())
        .get("/posts/1")
        .then()
        .statusCode(200);

    System.out.println("LoggingRecap — done.");
  }
}
