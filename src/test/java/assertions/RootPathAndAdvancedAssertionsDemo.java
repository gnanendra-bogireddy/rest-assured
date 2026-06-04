package assertions;

import common.TestApis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * rootPath / appendRootPath / noRootPath — shorten nested body assertions.
 */
public class RootPathAndAdvancedAssertionsDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/users/1")
        .then()
        .rootPath("address")
        .body("city", equalTo("Gwenborough"))
        .body("zipcode", equalTo("92998-2984"))
        .appendRootPath("geo")
        .body("lat", notNullValue())
        .noRootPath()
        .body("name", equalTo("Leanne Graham"));

  given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts")
        .then()
        .body("findAll { it.userId == 1 }.title", hasSize(10))
        .body("max { it.id }.id", equalTo(100));

    System.out.println("RootPathAndAdvancedAssertionsDemo — done.");
  }
}
