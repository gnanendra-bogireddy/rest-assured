package assertions;

import common.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Groovy GPath in body() — find, findAll, collect, everyItem (REST Assured docs).
 */
public class GroovyJsonBodyAssertionsDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/users")
        .then()
        .body("find { it.id == 3 }.name", equalTo("Clementine Bauch"))
        .body("findAll { it.address.city == 'Gwenborough' }.size()", greaterThan(0))
        .body("collect { it.email }", everyItem(containsString("@")));

    given()
        .baseUri(TestApis.DUMMY_JSON)
        .get("/products")
        .then()
        .body("products.find { it.id == 1 }.title", notNullValue());

    System.out.println("GroovyJsonBodyAssertionsDemo — done.");
  }
}
