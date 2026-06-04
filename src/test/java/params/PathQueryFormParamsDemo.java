package params;

import common.TestApis;
import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Path, query, and form parameters — REST Assured docs pattern.
 * <ul>
 *   <li>{@code pathParam} — {@code /users/{id}}</li>
 *   <li>{@code queryParam} — {@code ?limit=10&skip=0}</li>
 *   <li>{@code formParam} — {@code application/x-www-form-urlencoded} body</li>
 * </ul>
 */
public class PathQueryFormParamsDemo {

  public static void main(String[] args) {
    pathParamsJsonPlaceholder();
    queryParamsDummyJson();
    formParamsHttpbin();
    multiValueQueryParam();
    System.out.println("PathQueryFormParamsDemo — done.");
  }

  /** pathParam / pathParams — replaces placeholders in URI template */
  static void pathParamsJsonPlaceholder() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .pathParam("postId", 1)
        .get("/posts/{postId}")
        .then()
        .statusCode(200)
        .body("id", equalTo(1));
  }

  /** queryParam — DummyJSON products pagination (official docs style) */
  static void queryParamsDummyJson() {
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .queryParam("limit", 5)
        .queryParam("skip", 10)
        .get("/products")
        .then()
        .statusCode(200)
        .body("products.size()", equalTo(5))
        .body("skip", equalTo(10));

    Map<String, Object> search = Map.of("q", "phone");
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .queryParams(search)
        .get("/products/search")
        .then()
        .statusCode(200)
        .body("total", greaterThan(0));
  }

  /** formParam — OAuth-style token endpoint shape on httpbin */
  static void formParamsHttpbin() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.URLENC)
        .formParam("client_id", "demo-client")
        .formParam("grant_type", "client_credentials")
        .formParam("scope", "read", "write")
        .post("/post")
        .then()
        .statusCode(200)
        .body("form.client_id", equalTo("demo-client"))
        .body("form.scope", hasItems("read", "write"));
  }

  static void multiValueQueryParam() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .queryParam("sort", "name", "id")
        .get("/get")
        .then()
        .body("args.sort", hasItems("name", "id"));
  }
}
