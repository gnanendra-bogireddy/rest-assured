package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * ParserConfig / defaultParser — how REST Assured parses response body (JSON, XML, HTML, TEXT).
 */
public class ParserConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    // Global default when Content-Type is missing or ambiguous
    RestAssured.defaultParser = Parser.JSON;

    // Per-request parser registration (docs: registerParser)
    RestAssured.registerParser("application/vnd.custom+json", Parser.JSON);

    given()
        .baseUri(TestApis.HTTPBIN)
        .get("/xml")
        .then()
        .using()
        .defaultParser(Parser.XML)
        .body("slideshow.@title", equalTo("Sample Slide Show"));

    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().body("id", equalTo(1));

    RestAssured.unregisterParser("application/vnd.custom+json");
    RestAssured.reset();
    System.out.println("ParserConfigConcreteDemo — done.");
  }
}
