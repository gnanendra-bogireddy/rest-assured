package oauth;

import common.TestApis;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * OAuth2 client-credentials style — form body token request (httpbin echo).
 */
public class OAuth2ClientCredentialsDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.URLENC)
        .formParam("grant_type", "client_credentials")
        .formParam("client_id", "demo-client")
        .formParam("client_secret", "demo-secret")
        .post("/post")
        .then()
        .statusCode(200)
        .body("form.grant_type", org.hamcrest.Matchers.equalTo("client_credentials"));

    System.out.println("OAuth2ClientCredentialsDemo — done.");
  }
}
