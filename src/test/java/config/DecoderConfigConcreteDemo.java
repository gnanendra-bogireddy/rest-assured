package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.ContentDecoder.DEFLATE;
import static io.restassured.config.DecoderConfig.ContentDecoder.GZIP;
import static org.hamcrest.Matchers.equalTo;

/**
 * DecoderConfig — charset + GZIP/DEFLATE content decoders (httpbin supports gzip).
 */
public class DecoderConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .decoderConfig(
                DecoderConfig.decoderConfig()
                    .defaultContentCharset("UTF-8")
                    .useNoWrapForInflateDecoding(true)
                    .contentDecoders(GZIP, DEFLATE));

    given()
        .baseUri(TestApis.HTTPBIN)
        .header("Accept-Encoding", "gzip")
        .get("/gzip")
        .then()
        .statusCode(200)
        .body("gzipped", equalTo(true));

    RestAssured.reset();
    System.out.println("DecoderConfigConcreteDemo — done.");
  }
}
