package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

/**
 * Global REST Assured configuration (docs: "Configuration").
 * <p>
 * Static fields: {@code baseURI}, {@code port}, {@code basePath}, {@code config},
 * {@code requestSpecification}, {@code responseSpecification}, {@code defaultParser}.
 * Call {@link RestAssured#reset()} after suite to avoid leaking into other tests.
 */
public class GlobalConfigDemo {

  public static void main(String[] args) {
    RestAssured.baseURI = TestApis.JSON_PLACEHOLDER;
    RestAssured.basePath = "";
    RestAssured.port = -1; // derive from URI scheme

    RestAssured.useRelaxedHTTPSValidation();

    RequestSpecification reqSpec =
        new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addHeader("X-Global", "true")
            .build();
    RestAssured.requestSpecification = reqSpec;

    // Optional global response spec (avoid strict time SLA globally — flaky on slow networks)
    ResponseSpecification resSpec =
        new ResponseSpecBuilder().expectStatusCode(200).build();
    RestAssured.responseSpecification = resSpec;

    RestAssured.config =
        RestAssured.config()
            .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
            .redirect(RedirectConfig.redirectConfig().followRedirects(true))
            .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
            .sessionConfig(SessionConfig.sessionConfig().sessionIdName("JSESSIONID"));

    RestAssured.defaultParser = Parser.JSON;

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    given().get("/posts/1").then().statusCode(200);

    RestAssured.reset();
    System.out.println("GlobalConfigDemo — done.");
  }
}
