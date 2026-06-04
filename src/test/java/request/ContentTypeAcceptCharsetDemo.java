package request;

import common.TestApis;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * contentType(), accept(), charset — content negotiation.
 */
public class ContentTypeAcceptCharsetDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("/posts/1")
        .then()
        .contentType(ContentType.JSON)
        .body("id", equalTo(1));

    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType("application/json; charset=UTF-8")
        .body("{\"a\":1}")
        .post("/post")
        .then()
        .statusCode(200);

    System.out.println("ContentTypeAcceptCharsetDemo — done.");
  }
}
