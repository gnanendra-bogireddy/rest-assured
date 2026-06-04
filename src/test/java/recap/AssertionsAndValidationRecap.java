package recap;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

/**
 * Assertions — {@code then()} / {@code assertThat()} validation surface.
 * <p>
 * <b>Hamcrest</b> matchers ({@code equalTo}, {@code hasItem}, {@code everyItem}, etc.) are the default.
 * Multiple {@code .body()} calls in one {@code then()} are "soft" in spirit: RA collects failures
 * and reports them together (not TestNG soft assert — that's separate).
 * <p>
 * <b>rootPath / root()</b> — prefix for all following body assertions (DRY for nested JSON).
 * <p>
 * <b>JSON Schema</b> — contract testing via {@code json-schema-validator} module (classpath schema file).
 */
public class AssertionsAndValidationRecap {

  public static void main(String[] args) {
    demonstrateStatusAndHeaders();
    demonstrateBodyHamcrest();
    demonstrateRootPath();
    demonstrateJsonSchema();
    demonstrateResponseTime();
    System.out.println("AssertionsAndValidationRecap — done.");
  }

  private static void demonstrateStatusAndHeaders() {
    System.out.println("--- Status, status line, headers ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .assertThat()
        .statusCode(200)
        .statusLine(containsString("200"))
        .contentType(ContentType.JSON)
        .header("Content-Type", containsString("application/json"));
  }

  private static void demonstrateBodyHamcrest() {
    System.out.println("--- Body Hamcrest ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts")
        .then()
        .body("size()", equalTo(100))
        .body("id", hasItems(1, 2, 3))
        .body("[0].userId", equalTo(1))
        .body("title", not(empty()))
        .body("findAll { it.userId == 1 }.size()", equalTo(10));
  }

  /** rootPath applies to subsequent body() paths until {@code noRootPath()}. */
  private static void demonstrateRootPath() {
    System.out.println("--- rootPath ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/users/1")
        .then()
        .rootPath("address.geo")
        .body("lat", notNullValue())
        .body("lng", notNullValue())
        .noRootPath()
        .body("name", notNullValue());
  }

  private static void demonstrateJsonSchema() {
    System.out.println("--- JSON Schema ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
  }

  private static void demonstrateResponseTime() {
    System.out.println("--- Response time SLA ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .time(lessThan(5L), TimeUnit.SECONDS);
  }
}
