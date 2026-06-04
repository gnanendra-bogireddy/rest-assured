package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * EncoderConfig / DecoderConfig — charset for request body and response parsing.
 */
public class EncoderDecoderConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .encoderConfig(
                EncoderConfig.encoderConfig()
                    .defaultContentCharset("UTF-8")
                    .appendDefaultContentCharsetToContentTypeIfUndefined(false))
            .decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));

    // urlEncodingEnabled — avoid double-encoding pre-encoded query values
    given()
        .baseUri(TestApis.HTTPBIN)
        .urlEncodingEnabled(true)
        .queryParam("q", "hello world")
        .get("/get")
        .then()
        .body("args.q", equalTo("hello world"));

    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.URLENC)
        .formParam("name", "José")
        .post("/post")
        .then()
        .statusCode(200);

    RestAssured.urlEncodingEnabled = false;
    RestAssured.reset();
    System.out.println("EncoderDecoderConfigConcreteDemo — done.");
  }
}
