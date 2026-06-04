package assertions;

import common.TestApis;
import io.restassured.http.ContentType;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Response body validation in {@code then()} — status, headers, body, time (REST Assured docs).
 */
public class ResponseBodyValidationDemo {

  public static void main(String[] args) {
    singleObjectBody();
    jsonArrayBody();
    nestedJsonWithRootPath();
    responseTimeValidation();
    System.out.println("ResponseBodyValidationDemo — done.");
  }

  static void singleObjectBody() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("id", equalTo(1))
        .body("title", not(isEmptyOrNullString()))
        .body("body", containsString("quia"));
  }

  static void jsonArrayBody() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts")
        .then()
        .body("size()", equalTo(100))
        .body("id", hasItem(1))
        .body("[0].userId", equalTo(1))
        .body("userId", hasItems(1, 2, 3));
  }

  static void nestedJsonWithRootPath() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/users/1")
        .then()
        .rootPath("address.geo")
        .body("lat", notNullValue())
        .body("lng", notNullValue())
        .noRootPath()
        .body("name", equalTo("Leanne Graham"));
  }

  static void responseTimeValidation() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .time(lessThan(5L), TimeUnit.SECONDS);
  }
}
