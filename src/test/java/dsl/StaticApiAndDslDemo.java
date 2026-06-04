package dsl;

import common.TestApis;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Static DSL — {@code get/post/put/patch/delete/head/options} and {@code with()} (REST Assured docs).
 */
public class StaticApiAndDslDemo {

  public static void main(String[] args) {
    reset();
    baseURI = TestApis.JSON_PLACEHOLDER;

    get("/posts/1").then().statusCode(200);
    given().contentType(ContentType.JSON).body("{\"title\":\"t\",\"body\":\"b\",\"userId\":1}")
        .post("/posts").then().statusCode(201).body("id", notNullValue());

    Response r = with().queryParam("_limit", 2).get("/posts");
    r.then().body("size()", equalTo(2));

    given().contentType(ContentType.JSON).body("{\"title\":\"t\",\"body\":\"b\",\"userId\":1}")
        .when().post("/posts").then().statusCode(201);

    request(Method.GET, "/posts/1").then().body("id", equalTo(1));

    reset();
    System.out.println("StaticApiAndDslDemo — done.");
  }
}
