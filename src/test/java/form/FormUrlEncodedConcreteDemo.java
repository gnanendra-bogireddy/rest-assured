package form;

import common.TestApis;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * application/x-www-form-urlencoded — formParam (OAuth token endpoints, HTML forms).
 */
public class FormUrlEncodedConcreteDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.URLENC)
        .formParam("grant_type", "password")
        .formParam("username", "user")
        .formParam("password", "pass")
        .post("/post")
        .then()
        .statusCode(200)
        .body("form.grant_type", equalTo("password"));

    given()
        .baseUri(TestApis.HTTPBIN)
        .formParam("a", "1")
        .formParam("b", "2", "3")
        .post("/post")
        .then()
        .body("form.b", hasItems("2", "3"));

    System.out.println("FormUrlEncodedConcreteDemo — done.");
  }
}
