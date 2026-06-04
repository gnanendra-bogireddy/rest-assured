package validation;

import common.TestApis;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * ValidatableResponse — then() vs assertThat(), extract from validatable.
 */
public class ValidatableResponseDemo {

  public static void main(String[] args) {
    ValidatableResponse v =
        given()
            .baseUri(TestApis.JSON_PLACEHOLDER)
            .get("/posts/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(1));

    int id = v.extract().path("id");
    System.out.println("extracted id=" + id);

    System.out.println("ValidatableResponseDemo — done.");
  }
}
