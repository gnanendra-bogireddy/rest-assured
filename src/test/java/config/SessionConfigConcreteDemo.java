package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.SessionConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * SessionConfig — default session cookie name (JSESSIONID) and {@code sessionId()} DSL.
 */
public class SessionConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config().sessionConfig(SessionConfig.sessionConfig().sessionIdName("JSESSIONID"));

    given().baseUri(TestApis.HTTPBIN).sessionId("test-session-123").get("/cookies").then().statusCode(200);

    // Cookie from Set-Cookie then reuse session
    var cookies = given().baseUri(TestApis.HTTPBIN).get("/cookies/set?freecookie=1").detailedCookies();

    given()
        .baseUri(TestApis.HTTPBIN)
        .cookies(cookies)
        .get("/cookies")
        .then()
        .cookie("freecookie", equalTo("1"));

    RestAssured.reset();
    System.out.println("SessionConfigConcreteDemo — done.");
  }
}
