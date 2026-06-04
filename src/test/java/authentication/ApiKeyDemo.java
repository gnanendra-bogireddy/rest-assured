package authentication;

import common.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * API Key auth — header {@code X-API-Key} or query {@code api_key} (concrete httpbin echo).
 * See {@link AllAuthTypesDemo} for full auth matrix.
 */
public class ApiKeyDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .header("X-API-Key", "my-secret-api-key-12345")
        .get("/headers")
        .then()
        .statusCode(200)
        .body("headers.X-Api-Key", equalTo("my-secret-api-key-12345"));

    given()
        .baseUri(TestApis.HTTPBIN)
        .queryParam("api_key", "my-secret-api-key-12345")
        .get("/get")
        .then()
        .body("args.api_key", equalTo("my-secret-api-key-12345"));
  }
}
