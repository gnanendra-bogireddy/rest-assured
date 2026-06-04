package recap;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Reusable specifications & filters — framework-grade patterns.
 * <p>
 * <b>RequestSpecification</b> — shared headers, auth, baseUri (thread-safe when passed via {@code .spec()}).
 * <b>ResponseSpecification</b> — shared expected status, content-type, common body rules.
 * <p>
 * <b>Merge rules</b>: {@code given().spec(a).spec(b)} — later spec overrides earlier for same field.
 * {@code given().spec(global).header("X","override")} — inline wins.
 * <p>
 * <b>Filters</b> — intercept request/response; call {@code ctx.next(req, res)} to continue chain.
 * Built-in: {@code RequestLoggingFilter}, {@code ResponseLoggingFilter}, {@code ErrorLoggingFilter}.
 */
public class SpecificationsAndReuseRecap {

  public static void main(String[] args) {
    demonstrateRequestSpecReuse();
    demonstrateResponseSpecReuse();
    demonstrateExpectBuilder();
    demonstrateCustomFilter();
    System.out.println("SpecificationsAndReuseRecap — done.");
  }

  private static void demonstrateRequestSpecReuse() {
    System.out.println("--- RequestSpecification ---");
    RequestSpecification common =
        new RequestSpecBuilder()
            .setBaseUri("https://jsonplaceholder.typicode.com")
            .setContentType(ContentType.JSON)
            .addHeader("X-Test-Suite", "recap")
            .build();

    given().spec(common).get("/posts/1").then().statusCode(200);
    given().spec(common).get("/posts/2").then().statusCode(200);

    // Inline spec from given() — mutable reuse
    RequestSpecification inline =
        given().baseUri("https://jsonplaceholder.typicode.com").pathParam("id", 1);
    given().spec(inline).get("/posts/{id}").then().body("id", equalTo(1));
    given().spec(inline).pathParam("id", 2).get("/posts/{id}").then().body("id", equalTo(2));
  }

  private static void demonstrateResponseSpecReuse() {
    System.out.println("--- ResponseSpecification ---");
    ResponseSpecification okJson =
        new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .spec(okJson)
        .body("id", equalTo(1));
  }

  /** {@code RestAssured.expect()} builds a ResponseSpecification without a request. */
  private static void demonstrateExpectBuilder() {
    System.out.println("--- RestAssured.expect() ---");
    ResponseSpecification expect200 =
        RestAssured.expect().statusCode(200).contentType(ContentType.JSON);

    given().baseUri("https://httpbin.org").get("/get").then().spec(expect200);
  }

  private static void demonstrateCustomFilter() {
    System.out.println("--- Custom filter ---");
    Filter timingFilter =
        (FilterableRequestSpecification req,
            FilterableResponseSpecification res,
            FilterContext ctx) -> {
          long start = System.currentTimeMillis();
          Response response = ctx.next(req, res);
          System.out.printf(
              "FILTER %s %s → %d in %d ms%n",
              req.getMethod(), req.getURI(), response.statusCode(), System.currentTimeMillis() - start);
          return response;
        };

    given()
        .filter(timingFilter)
        .baseUri("https://jsonplaceholder.typicode.com")
        .get("/posts/1")
        .then()
        .statusCode(200);
  }
}
