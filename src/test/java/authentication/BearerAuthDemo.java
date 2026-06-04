package authentication;

import common.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Bearer / OAuth2 token — {@code auth().oauth2(token)} (see {@link AllAuthTypesDemo} for DummyJSON login).
 */
public class BearerAuthDemo {

  public static void main(String[] args) {
    String token = "eyJhbGciOiJIUzI1NiJ9.demo";

    given().auth().oauth2(token).get(TestApis.HTTPBIN + "/bearer").then().statusCode(200);

    given()
        .header("Authorization", "Bearer " + token)
        .get(TestApis.HTTPBIN + "/bearer")
        .then()
        .body("token", equalTo(token));
  }
}
