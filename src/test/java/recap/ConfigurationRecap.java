package recap;

import io.restassured.RestAssured;
import io.restassured.config.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;

/**
 * RestAssured.config — fine-grained control; prefer per-request {@code .config()} in parallel suites.
 * <p>
 * Common configs: {@link LogConfig}, {@link SSLConfig}, {@link RedirectConfig},
 * {@link ConnectionConfig}, {@link EncoderConfig}, {@link DecoderConfig}, {@link SessionConfig},
 * {@link HttpClientConfig}, {@link ParserConfig}.
 */
public class ConfigurationRecap {

  public static void main(String[] args) {
    demonstrateGlobalAndPerRequestConfig();
    demonstrateSslAndRelaxedHttps();
    demonstrateRedirectAndEncoder();
    demonstrateSessionId();
    demonstrateParserDefault();
    RestAssured.reset();
    System.out.println("ConfigurationRecap — done.");
  }

  private static void demonstrateGlobalAndPerRequestConfig() {
    System.out.println("--- Log on validation failure ---");
    RestAssured.config =
        RestAssured.config()
            .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .statusCode(200);
  }

  private static void demonstrateSslAndRelaxedHttps() {
    System.out.println("--- Relaxed HTTPS (dev/stage self-signed certs) ---");
    // RestAssured.useRelaxedHTTPSValidation(); // trust all certs — NEVER in prod tests against real env
    // Per request:
    given().relaxedHTTPSValidation().baseUri("https://jsonplaceholder.typicode.com").get("/posts/1").then().statusCode(200);
  }

  private static void demonstrateRedirectAndEncoder() {
    System.out.println("--- Redirect & URL encoding ---");
    RestAssured.config =
        RestAssured.config()
            .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(5));

    // Disable encoding when params are already encoded (avoid double-encoding)
    given()
        .baseUri("https://httpbin.org")
        .urlEncodingEnabled(false)
        .queryParam("q", "a b")
        .get("/get")
        .then()
        .statusCode(200);
  }

  private static void demonstrateSessionId() {
    System.out.println("--- Session cookie name ---");
    RestAssured.config =
        RestAssured.config()
            .sessionConfig(SessionConfig.sessionConfig().sessionIdName("JSESSIONID"));
    // .sessionId("value") on given() attaches session to subsequent calls in same thread
  }

  private static void demonstrateParserDefault() {
    System.out.println("--- Default parser ---");
    RestAssured.defaultParser = Parser.JSON;
    // Parser.JSON | XML | HTML — when Content-Type is ambiguous
  }
}
