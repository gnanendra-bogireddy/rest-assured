package specification;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * RequestSpecification — reuse request defaults (REST Assured docs: "Reusing specifications").
 * <p>
 * Build with {@link RequestSpecBuilder} or {@code RestAssured.given()...} then pass {@code .spec(spec)}.
 * Merge: later {@code given().spec(a).header("X","2")} overrides earlier values.
 */
public class RequestSpecificationConcreteDemo {

  public static void main(String[] args) {
  RequestSpecification jsonPlaceholderSpec =
      new RequestSpecBuilder()
          .setBaseUri(TestApis.JSON_PLACEHOLDER)
          .setContentType(ContentType.JSON)
          .setAccept(ContentType.JSON)
          .addHeader("X-Client", "rest-assured-recap")
          .build();

  given().spec(jsonPlaceholderSpec).get("/posts/1").then().statusCode(200).body("id", equalTo(1));

  given().spec(jsonPlaceholderSpec).get("/posts/2").then().body("id", equalTo(2));

  // Inline spec from given() — mutable for pathParam changes
  RequestSpecification userPosts =
      given().baseUri(TestApis.JSON_PLACEHOLDER).queryParam("userId", 1);
  given().spec(userPosts).get("/posts").then().statusCode(200).body("[0].userId", equalTo(1));

  // Global spec (not thread-safe for parallel — prefer .spec() per test)
  RestAssured.requestSpecification = jsonPlaceholderSpec;
  RestAssured.get("/posts/3").then().body("id", equalTo(3));
  RestAssured.reset();

  System.out.println("RequestSpecificationConcreteDemo — done.");
  }
}
