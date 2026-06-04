package logging;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Request & response logging — REST Assured docs (DSL + filters + LogConfig).
 * <p>
 * <b>Request</b> (on {@code given()}): {@code log().all() | .uri() | .method() | .headers() | .cookies() | .params() | .body()}
 * <p>
 * <b>Response</b> (on {@code then()}): {@code log().all() | .body() | .status() | .headers() | .cookies() |
 * .ifError() | .ifValidationFails() | .ifStatusCodeIsEqualTo(n)}
 * <p>
 * <b>Filters</b>: {@link RequestLoggingFilter}, {@link ResponseLoggingFilter}, {@link ErrorLoggingFilter}
 */
public class LoggingConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();
    requestLoggingDsl();
    responseLoggingDsl();
    loggingFilters();
    logOnValidationFailure();
    blacklistSensitiveHeaders();
    RestAssured.reset();
    System.out.println("LoggingConcreteDemo — done (check console output above).");
  }

  static void requestLoggingDsl() {
    System.out.println("\n=== Request log DSL ===");
    var spec =
        given().baseUri(TestApis.JSON_PLACEHOLDER).queryParam("postId", 1).header("X-Demo", "logging");

    spec.log().uri().get("/comments").then().statusCode(200);
    spec.log().params().get("/comments").then().statusCode(200);
    spec.log().headers().get("/comments").then().statusCode(200);
    spec.log().method().get("/comments").then().statusCode(200);
    spec.log().all().get("/comments").then().statusCode(200);
  }

  static void responseLoggingDsl() {
    System.out.println("\n=== Response log DSL ===");
    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().log().status().statusCode(200);
    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().log().headers().statusCode(200);
    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().log().body().statusCode(200);
    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().log().all().statusCode(200);

    // Only when status >= 400
    given().baseUri(TestApis.HTTPBIN).get("/status/404").then().log().ifError().statusCode(404);

    given().baseUri(TestApis.HTTPBIN).get("/get").then().log().ifStatusCodeIsEqualTo(200).statusCode(200);
  }

  static void loggingFilters() {
    System.out.println("\n=== Logging filters ===");
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
        .get("/posts/1")
        .then()
        .statusCode(200);

    given()
        .baseUri(TestApis.HTTPBIN)
        .filter(new ErrorLoggingFilter())
        .get("/status/500")
        .then()
        .statusCode(500);
  }

  static void logOnValidationFailure() {
    System.out.println("\n=== Log if validation fails ===");
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    try {
      given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().body("id", equalTo(999));
    } catch (AssertionError expected) {
      System.out.println("(Expected assertion failure — request/response should be logged)");
    }

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .log()
        .ifValidationFails()
        .get("/posts/1")
        .then()
        .statusCode(200);
  }

  static void blacklistSensitiveHeaders() {
    System.out.println("\n=== Blacklist headers in logs ===");
    given()
        .config(config().logConfig(LogConfig.logConfig().blacklistHeader("Content-Type")))
        .baseUri(TestApis.HTTPBIN)
        .get("/get")
        .then()
        .log()
        .headers()
        .statusCode(200);
  }
}
