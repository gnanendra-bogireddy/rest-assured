package assertions;

import common.TestApis;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

/**
 * JSON Schema validation — requires {@code json-schema-validator} module (see pom.xml).
 * <p>
 * Validates response structure (types, required fields) — contract / API testing.
 * <p>
 * Methods:
 * <ul>
 *   <li>{@code matchesJsonSchemaInClasspath("schemas/post-schema.json")}</li>
 *   <li>{@code matchesJsonSchema(new File("..."))}</li>
 *   <li>{@code JsonSchemaValidator.settings().with()...} for draft / reporting options</li>
 * </ul>
 */
public class JsonSchemaValidationConcreteDemo {

  public static void main(String[] args) {
    validatePostFromClasspath();
    validateFromFile();
    validateDummyJsonProductShape();
    System.out.println("JsonSchemaValidationConcreteDemo — done.");
  }

  static void validatePostFromClasspath() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
  }

  /** matchesJsonSchema(File) — schema from filesystem */
  static void validateFromFile() {
    File schema =
        new File("src/test/resources/schemas/post-schema.json").getAbsoluteFile();
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .body(JsonSchemaValidator.matchesJsonSchema(schema));
  }

  /** Inline schema via File (same schema copied path for demo) */
  static void validateDummyJsonProductShape() {
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .get("/products/1")
        .then()
        .statusCode(200)
        .body("id", greaterThan(0))
        .body("title", not(emptyOrNullString()))
        .body("price", greaterThan(0f))
        .body(matchesJsonSchemaInClasspath("schemas/dummyjson-product-schema.json"));
  }
}
