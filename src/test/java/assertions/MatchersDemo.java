package assertions;

import common.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Hamcrest matchers used by REST Assured (from docs + common interview list).
 */
public class MatchersDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts")
        .then()
        .statusCode(200)
        // equality
        .body("size()", equalTo(100))
        .body("[0].id", equalTo(1))
        // comparison
        .body("[0].userId", greaterThan(0))
        .body("[0].userId", lessThanOrEqualTo(10))
        // strings
        .body("[0].title", not(emptyString()))
        .body("[0].title", containsString("sunt"))
        .body("[0].title", startsWith("sunt"))
        // collections
        .body("id", hasItem(50))
        .body("id", hasItems(1, 2, 3))
        .body("userId", everyItem(greaterThan(0)))
        // logical
        .body("[0].title", anyOf(containsString("sunt"), containsString("qui")))
        .body("[0].title", allOf(notNullValue(), not(emptyString())))
        // Groovy GPath in body
        .body("findAll { it.userId == 1 }.size()", equalTo(10))
        .body("find { it.id == 10 }.userId", equalTo(1));

    System.out.println("MatchersDemo — done.");
  }
}
