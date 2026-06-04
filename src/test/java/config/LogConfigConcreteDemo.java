package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * LogConfig — global logging behaviour (blacklist headers, log on validation failure).
 */
public class LogConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .logConfig(
                LogConfig.logConfig()
                    .blacklistDefaultSensitiveHeaders()
                    .blacklistHeader("Authorization")
                    .enableLoggingOfRequestAndResponseIfValidationFails());

    try {
      given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().body("id", equalTo(-1));
    } catch (AssertionError e) {
      System.out.println("LogConfig: validation failed — RA logged request/response per config");
    }

    RestAssured.reset();
    System.out.println("LogConfigConcreteDemo — done.");
  }
}
