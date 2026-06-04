package assertions;

import common.TestApis;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * JSON Schema — TestNG variant. Runnable: {@link JsonSchemaValidationConcreteDemo#main}.
 */
public class JsonSchemaValidationDemo {

  @Test
  public void testPostSchema() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"));
  }
}
