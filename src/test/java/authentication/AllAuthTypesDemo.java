package authentication;

import common.TestApis;
import common.auth.DummyJsonTokenStore;
import common.auth.LoginRequest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * All common auth styles with concrete calls.
 * <ul>
 *   <li>API Key — header or query (httpbin echoes; petstore-style header)</li>
 *   <li>Basic — {@code auth().basic(user, pass)}</li>
 *   <li>Preemptive Basic — send credentials without waiting for 401</li>
 *   <li>Bearer / OAuth2 — {@code auth().oauth2(token)} or Authorization header</li>
 *   <li>OAuth2 login — DummyJSON {@code /auth/login} then Bearer on protected routes</li>
 *   <li>Digest — {@code auth().digest(user, pass)} (httpbin)</li>
 * </ul>
 */
public class AllAuthTypesDemo {

  public static void main(String[] args) {
    io.restassured.RestAssured.reset();
    apiKeyInHeader();
    apiKeyInQuery();
    basicAuth();
    preemptiveBasicAuth();
    bearerTokenHttpbin();
    oauth2LoginDummyJson();
    digestAuth();
    System.out.println("AllAuthTypesDemo — done.");
  }

  /** API key in header (common for B2B APIs) */
  static void apiKeyInHeader() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .header("X-API-Key", "my-secret-api-key-12345")
        .get("/headers")
        .then()
        .statusCode(200)
        .body("headers.X-Api-Key", equalTo("my-secret-api-key-12345"));
  }

  /** API key as query parameter (?api_key=...) */
  static void apiKeyInQuery() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .queryParam("api_key", "my-secret-api-key-12345")
        .get("/get")
        .then()
        .statusCode(200)
        .body("args.api_key", equalTo("my-secret-api-key-12345"));
  }

  /** HTTP Basic — challenge/response; httpbin /basic-auth/{user}/{passwd} */
  static void basicAuth() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .auth()
        .basic(TestApis.HTTPBIN_BASIC_USER, TestApis.HTTPBIN_BASIC_PASS)
        .get("/basic-auth/{user}/{pass}", TestApis.HTTPBIN_BASIC_USER, TestApis.HTTPBIN_BASIC_PASS)
        .then()
        .statusCode(200)
        .body("authenticated", equalTo(true));
  }

  static void preemptiveBasicAuth() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .auth()
        .preemptive()
        .basic(TestApis.HTTPBIN_BASIC_USER, TestApis.HTTPBIN_BASIC_PASS)
        .get("/basic-auth/{user}/{pass}", TestApis.HTTPBIN_BASIC_USER, TestApis.HTTPBIN_BASIC_PASS)
        .then()
        .statusCode(200);
  }

  /** Bearer token — httpbin /bearer echoes Authorization */
  static void bearerTokenHttpbin() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.demo";

    given().auth().oauth2(token).get(TestApis.HTTPBIN + "/bearer").then().statusCode(200);

    given()
        .header("Authorization", "Bearer " + token)
        .get(TestApis.HTTPBIN + "/bearer")
        .then()
        .statusCode(200)
        .body("authenticated", equalTo(true))
        .body("token", equalTo(token));
  }

  /** OAuth2-style: obtain accessToken from login, use on /auth/me */
  static void oauth2LoginDummyJson() {
    DummyJsonTokenStore store = new DummyJsonTokenStore();
    store.login(TestApis.DUMMYJSON_USER, TestApis.DUMMYJSON_PASS);

    given()
        .baseUri(TestApis.DUMMY_JSON)
        .header("Authorization", "Bearer " + store.getAccessToken())
        .get("/auth/me")
        .then()
        .statusCode(200)
        .body("username", equalTo(TestApis.DUMMYJSON_USER));

    // Same flow using body login + extract (docs style)
    String token =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .contentType(ContentType.JSON)
            .body(new LoginRequest(TestApis.DUMMYJSON_USER, TestApis.DUMMYJSON_PASS))
            .post("/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .path("accessToken");

    given()
        .auth()
        .oauth2(token)
        .get(TestApis.DUMMY_JSON + "/user/1")
        .then()
        .statusCode(200)
        .body("id", equalTo(1));
  }

  static void digestAuth() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .auth()
        .digest("user", "passwd")
        .get("/digest-auth/auth/user/passwd")
        .then()
        .statusCode(200);
  }
}
