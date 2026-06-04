package http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * REST Assured SME Recap: GET Requests
 * 
 * Key Interview Points:
 * 1. Given-When-Then: The BDD style syntax (Gherkin-like).
 * 2. Path Parameters vs Query Parameters: 
 *    - Path params: part of the URL path (/{id}).
 *    - Query params: after the '?' (?id=1).
 * 3. Log().all(): Essential for debugging.
 * 4. Hamcrest Matchers: Used in .then().body() for assertions.
 */
public class GetRequestDemo {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Basic GET request with logging")
    public void testBasicGet() {
        RestAssured.given()
                .log().all() // Log request details
                .when()
                .get("/posts")
                .then()
                .log().ifError() // Log response only if error occurs
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test(description = "GET request with Path Parameter")
    public void testWithPathParam() {
        RestAssured.given()
                .pathParam("postId", 1)
                .when()
                .get("/posts/{postId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test(description = "GET request with Query Parameter")
    public void testWithQueryParam() {
        RestAssured.given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // Validate list size
                .body("[0].userId", equalTo(1)); // Validate first element in list
    }

    @Test(description = "Complex Path Parameter example")
    public void testNestedResource() {
        RestAssured.given()
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}/comments")
                .then()
                .statusCode(200)
                .body("postId", everyItem(equalTo(1))); // Groovy matcher everyItem
    }

    @Test(description = "Query map, accept header, and _limit")
    public void testQueryMapAndAccept() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .queryParams(Map.of("_limit", "5"))
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(5));
    }

    /** Runnable without TestNG — use for quick IDE execution. */
    public static void main(String[] args) {
        GetRequestDemo demo = new GetRequestDemo();
        demo.setup();
        demo.testBasicGet();
        demo.testWithPathParam();
        demo.testWithQueryParam();
        demo.testNestedResource();
        demo.testQueryMapAndAccept();
        RestAssured.reset();
        System.out.println("GetRequestDemo — all examples passed.");
    }
}
