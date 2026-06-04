package authentication;

import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * OAuth 1.0 signing — requires scribejava-apis on classpath (see pom.xml).
 * Uses Postman echo OAuth1 test endpoint (REST Assured docs pattern).
 */
public class OAuth1ConcreteDemo {

  public static void main(String[] args) {
    given()
        .baseUri("https://postman-echo.com")
        .auth()
        .oauth(
            "RKCGzna7bv9YD57c",
            "D+EdQ-gs$-%@2Nu7",
            "",
            "",
            OAuthSignature.HEADER)
        .contentType(ContentType.MULTIPART)
        .multiPart("code", "xWnkliVQJURqB2x1")
        .multiPart("grant_type", "authorization_code")
        .multiPart("client_id", "abc123")
        .get("/oauth1")
        .then()
        .statusCode(200);

    System.out.println("OAuth1ConcreteDemo — done.");
  }
}
