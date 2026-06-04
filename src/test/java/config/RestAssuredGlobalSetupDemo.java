package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Full global setup pattern — baseURI, port, basePath, specs, config, reset() (interview / framework).
 */
public class RestAssuredGlobalSetupDemo {

  public static void main(String[] args) {
    RestAssured.baseURI = TestApis.JSON_PLACEHOLDER;
    RestAssured.basePath = "";
    RestAssured.useRelaxedHTTPSValidation();

    RestAssured.requestSpecification =
        new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addHeader("X-Suite", "global-setup")
            .build();

    RestAssured.responseSpecification =
        new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    RestAssured.config =
        RestAssured.config()
            .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
            .sslConfig(SSLConfig.sslConfig().allowAllHostnames());

    RestAssured.defaultParser = Parser.JSON;

    given().get("/posts/1").then().body("id", equalTo(1));

    RestAssured.reset();
    System.out.println("RestAssuredGlobalSetupDemo — done.");
  }
}
