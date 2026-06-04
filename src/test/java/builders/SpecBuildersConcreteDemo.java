package builders;

import common.TestApis;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * RequestSpecBuilder & ResponseSpecBuilder — full builder API (docs).
 */
public class SpecBuildersConcreteDemo {

  public static void main(String[] args) {
    RequestSpecification req =
        new RequestSpecBuilder()
            .setBaseUri(TestApis.JSON_PLACEHOLDER)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("X-Builder", "true")
            .addQueryParam("_limit", 1)
            .addFilter(new ErrorLoggingFilter())
            .build();

    ResponseSpecification res =
        new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    given().spec(req).get("/posts").then().spec(res).body("size()", equalTo(1));

    System.out.println("SpecBuildersConcreteDemo — done.");
  }
}
