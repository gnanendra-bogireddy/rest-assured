package http;

import common.TestApis;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * All HTTP methods with JSON dummy APIs (JSONPlaceholder + DummyJSON + httpbin).
 */
public class AllHttpMethodsDemo {

  public static void main(String[] args) {
    getExamples();
    postExamples();
    putExamples();
    patchExamples();
    deleteExamples();
    headAndOptions();
    genericRequestMethod();
    System.out.println("AllHttpMethodsDemo — done.");
  }

  static void getExamples() {
    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().statusCode(200);
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .queryParam("limit", 3)
        .get("/products")
        .then()
        .body("products.size()", equalTo(3));
  }

  static void postExamples() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .contentType(ContentType.JSON)
        .body(Map.of("title", "new", "body", "b", "userId", 1))
        .post("/posts")
        .then()
        .statusCode(201);

    given()
        .baseUri(TestApis.DUMMY_JSON)
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Test Product", "description", "demo"))
        .post("/products/add")
        .then()
        .statusCode(200)
        .body("title", equalTo("Test Product"));
  }

  static void putExamples() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .contentType(ContentType.JSON)
        .body(Map.of("id", 1, "title", "updated", "body", "b", "userId", 1))
        .put("/posts/1")
        .then()
        .statusCode(200);

    given()
        .baseUri(TestApis.DUMMY_JSON)
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Updated Phone"))
        .put("/products/1")
        .then()
        .statusCode(200);
  }

  static void patchExamples() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .contentType(ContentType.JSON)
        .body(Map.of("title", "patched"))
        .patch("/posts/1")
        .then()
        .statusCode(200);

    given()
        .baseUri(TestApis.DUMMY_JSON)
        .contentType(ContentType.JSON)
        .body(Map.of("price", 9.99))
        .patch("/products/1")
        .then()
        .statusCode(200);
  }

  static void deleteExamples() {
    given().baseUri(TestApis.JSON_PLACEHOLDER).delete("/posts/1").then().statusCode(200);
    given().baseUri(TestApis.DUMMY_JSON).delete("/products/1").then().statusCode(200);
  }

  static void headAndOptions() {
    given().baseUri(TestApis.HTTPBIN).head("/get").then().statusCode(200);
    given().baseUri(TestApis.HTTPBIN).options("/get").then().statusCode(200);
  }

  static void genericRequestMethod() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .request(Method.GET, "/posts/1")
        .then()
        .body("id", equalTo(1));
  }
}
