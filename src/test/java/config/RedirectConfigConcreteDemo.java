package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * RedirectConfig — follow redirects, max hops, circular redirects (httpbin /redirect/n).
 */
public class RedirectConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .redirect(
                RedirectConfig.redirectConfig()
                    .followRedirects(true)
                    .maxRedirects(5)
                    .allowCircularRedirects(false));

    // httpbin /redirect/2 → follows 2 redirects → 200
    given()
        .baseUri(TestApis.HTTPBIN)
        .redirects()
        .follow(true)
        .and()
        .redirects()
        .max(5)
        .get("/redirect/2")
        .then()
        .statusCode(200);

    // DSL without changing global config
    given()
        .baseUri(TestApis.HTTPBIN)
        .redirects()
        .follow(false)
        .get("/redirect/1")
        .then()
        .statusCode(302);

    RestAssured.reset();
    System.out.println("RedirectConfigConcreteDemo — done.");
  }
}
