package recap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * REST Assured DSL & HTTP methods — interview essentials.
 * <p>
 * <b>Core mental model</b>
 * <ul>
 *   <li>{@code given()} — request setup (auth, headers, body, params)</li>
 *   <li>{@code when()} — HTTP verb (optional; {@code get()} after {@code given()} implies when)</li>
 *   <li>{@code then()} — validations; throws {@link AssertionError} on failure</li>
 * </ul>
 * <b>Static shortcuts</b> (import {@code RestAssured.*}): {@code get("/x")} equals
 * {@code given().when().get("/x")} but uses global {@code baseURI}, {@code port}, {@code basePath}.
 * <p>
 * <b>Thread safety</b>: global static fields on {@link RestAssured} are NOT safe for parallel tests
 * with different configs — use {@code .spec(requestSpec)} per thread instead.
 */
public class DslAndHttpMethodsRecap {

    private static final String BASE = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        demonstrateGivenWhenThen();
        demonstrateStaticShortcuts();
        demonstrateAllHttpVerbs();
        demonstrateRequestMethodOverload();

        RestAssured.reset();
        System.out.println("DslAndHttpMethodsRecap — done.");
    }

    /** Classic BDD flow; each block can be omitted but order is conventional. */
    private static void demonstrateGivenWhenThen() {
        System.out.println("--- Given-When-Then ---");
        given()
                .baseUri(BASE)
                .header("Accept", "application/json")
                .pathParam("id", 1)
                .log().uri()
        .when()
                .get("/posts/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    /**
     * Static {@code get/post/put/patch/delete/head/options} skip explicit given/when when
     * globals or inline {@code baseUri} are enough.
     */
    private static void demonstrateStaticShortcuts() {
        System.out.println("--- Static shortcuts ---");
        baseURI = BASE;

        // Shortest form
        get("/posts/1").then().statusCode(200);

        // with() merges a partial spec into the call
        Response r = with()
                .queryParam("_limit", 2)
                .get("/posts");
        r.then().body("size()", equalTo(2));

        RestAssured.reset();
    }

    private static void demonstrateAllHttpVerbs() {
        System.out.println("--- HTTP verbs ---");

        // GET — read
        given().baseUri(BASE).get("/posts/1").then().statusCode(200);

        // POST — create (jsonplaceholder returns 201)
        given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"title\":\"t\",\"body\":\"b\",\"userId\":1}")
                .post("/posts")
                .then().statusCode(201);

        // PUT — full replace (fake API returns 200)
        given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"id\":1,\"title\":\"updated\",\"body\":\"b\",\"userId\":1}")
                .put("/posts/1")
                .then().statusCode(200);

        // PATCH — partial update
        given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"title\":\"patched\"}")
                .patch("/posts/1")
                .then().statusCode(200);

        // DELETE
        given().baseUri(BASE).delete("/posts/1").then().statusCode(200);

        // HEAD — headers only, no body
        given().baseUri("https://httpbin.org").head("/get")
                .then().statusCode(200).header("Content-Type", containsString("application/json"));

        // OPTIONS — allowed methods
        given().baseUri("https://httpbin.org").options("/get")
                .then().statusCode(200);
    }

    /**
     * {@code .request(Method, path)} — single entry for dynamic verb selection (data-driven tests).
     */
    private static void demonstrateRequestMethodOverload() {
        System.out.println("--- request(Method, path) ---");
        Response response = given()
                .baseUri(BASE)
                .request(Method.GET, "/posts/1");
        ValidatableResponse validatable = response.then();
        validatable.statusCode(200).body("userId", greaterThan(0));
    }
}
