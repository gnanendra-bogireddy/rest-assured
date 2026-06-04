package specification;

import common.TestApis;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * ResponseSpecification — reuse response expectations (REST Assured docs).
 * <p>
 * {@code RestAssured.expect()} or {@link ResponseSpecBuilder}; apply with {@code .then().spec(responseSpec)}.
 */
public class ResponseSpecificationConcreteDemo {

  public static void main(String[] args) {
    ResponseSpecification okJson =
        new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .expectResponseTime(lessThan(10L), TimeUnit.SECONDS)
            .build();

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .get("/posts/1")
        .then()
        .spec(okJson)
        .body("userId", greaterThan(0));

    // RestAssured.expect() — docs shortcut for ResponseSpecification
    ResponseSpecification httpbinGet =
        expect().statusCode(200).contentType(containsString("application/json"));

    given().baseUri(TestApis.HTTPBIN).get("/get").then().spec(httpbinGet);

    System.out.println("ResponseSpecificationConcreteDemo — done.");
  }
}
