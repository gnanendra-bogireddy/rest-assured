package chained;

import common.TestApis;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Chained API calls — extract from first response, use in second request (interview pattern).
 */
public class ChainedApiCallsDemo {

  public static void main(String[] args) {
    int userId =
        given()
            .baseUri(TestApis.JSON_PLACEHOLDER)
            .get("/users/1")
            .then()
            .extract()
            .path("id");

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .queryParam("userId", userId)
        .get("/posts")
        .then()
        .statusCode(200)
        .body("userId", everyItem(equalTo(userId)));

    String accessToken =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .contentType(ContentType.JSON)
            .body(
                "{\"username\":\""
                    + TestApis.DUMMYJSON_USER
                    + "\",\"password\":\""
                    + TestApis.DUMMYJSON_PASS
                    + "\"}")
            .post("/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .path("accessToken");

    given()
        .header("Authorization", "Bearer " + accessToken)
        .get(TestApis.DUMMY_JSON + "/auth/me")
        .then()
        .body("username", equalTo(TestApis.DUMMYJSON_USER));

    System.out.println("ChainedApiCallsDemo — done.");
  }
}
