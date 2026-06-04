package recap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Building HTTP requests — every common {@code given()} modifier.
 * <p>
 * <b>Params cheat sheet</b>
 * <table>
 *   <tr><td>{@code pathParam}</td><td>Replaces {@code {name}} in URL path</td></tr>
 *   <tr><td>{@code queryParam}</td><td>Appended after {@code ?} (GET filters, pagination)</td></tr>
 *   <tr><td>{@code param}</td><td>Query for GET; form field for POST (depends on method)</td></tr>
 *   <tr><td>{@code formParam}</td><td>{@code application/x-www-form-urlencoded} body fields</td></tr>
 *   <tr><td>{@code multiPart}</td><td>{@code multipart/form-data} (file uploads)</td></tr>
 * </table>
 * <b>Content negotiation</b>: {@code contentType(JSON)} sets request body type;
 * {@code accept(JSON)} sets Accept header for response format preference.
 */
public class RequestBuildingRecap {

  public static void main(String[] args) {
    demonstrateUriParts();
    demonstrateParams();
    demonstrateHeadersAndCookies();
    demonstrateBodyFormats();
    demonstrateFormAndMultipart();
    demonstrateFileAndClasspathBody();
    System.out.println("RequestBuildingRecap — done.");
  }

  /** baseUri + basePath + path = full URL. port defaults to 8080 unless set or implied by https. */
  private static void demonstrateUriParts() {
    System.out.println("--- URI parts ---");
    given()
        .baseUri("https://httpbin.org")
        .basePath("/")
        .get("get")
        .then()
        .statusCode(200)
        .body("url", containsString("httpbin.org/get"));
  }

  private static void demonstrateParams() {
    System.out.println("--- Path & query params ---");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .pathParam("postId", 1)
        .queryParam("comments", true) // ignored by API; shows syntax
        .get("/posts/{postId}/comments")
        .then()
        .statusCode(200)
        .body("postId", everyItem(equalTo(1)));

    // Map overload
    Map<String, String> query = Map.of("_limit", "3");
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .queryParams(query)
        .get("/posts")
        .then()
        .body("size()", equalTo(3));

    // Multi-value query: ?tag=a&tag=b
    given()
        .baseUri("https://httpbin.org")
        .queryParam("tag", "java", "rest-assured")
        .get("/get")
        .then()
        .body("args.tag", hasItems("java", "rest-assured"));
  }

  private static void demonstrateHeadersAndCookies() {
    System.out.println("--- Headers & cookies ---");
    RequestSpecification spec =
        given()
            .baseUri("https://httpbin.org")
            .header("X-Custom", "interview-prep")
            .headers("Accept", "application/json", "X-Trace", "1")
            .cookie("session_id", "abc123")
            .cookies("pref", "dark", "lang", "en");

    spec.get("/cookies/set")
        .then()
        .statusCode(200);

    // Reuse cookies from a response in the next request
    Cookies cookies =
        given().baseUri("https://httpbin.org").get("/cookies/set?freecookie=test").detailedCookies();
    given()
        .baseUri("https://httpbin.org")
        .cookies(cookies)
        .get("/cookies")
        .then()
        .cookie("freecookie", equalTo("test"));
  }

  private static void demonstrateBodyFormats() {
    System.out.println("--- Request bodies ---");

    // 1) Raw JSON string
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body("{\"title\":\"x\",\"body\":\"y\",\"userId\":1}")
        .post("/posts")
        .then()
        .statusCode(201);

    // 2) Map → serialized to JSON when Content-Type is JSON
    Map<String, Object> map = new HashMap<>();
    map.put("title", "from-map");
    map.put("body", "body");
    map.put("userId", 1);
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body(map)
        .post("/posts")
        .then()
        .statusCode(201)
        .body("title", equalTo("from-map"));

    // 3) List as JSON array body
    given()
        .baseUri("https://httpbin.org")
        .contentType(ContentType.JSON)
        .body(List.of(1, 2, 3))
        .post("/post")
        .then()
        .body("json", equalTo(List.of(1, 2, 3)));
  }

  private static void demonstrateFormAndMultipart() {
    System.out.println("--- Form URL encoded ---");
    given()
        .baseUri("https://httpbin.org")
        .contentType(ContentType.URLENC)
        .formParam("grant_type", "client_credentials")
        .formParam("scope", "read", "write")
        .post("/post")
        .then()
        .body("form.grant_type", equalTo("client_credentials"));

    System.out.println("--- Multipart (file upload) ---");
    given()
        .baseUri("https://httpbin.org")
        .multiPart("file", "demo.txt", "hello".getBytes(), "text/plain")
        .formParam("description", "interview recap")
        .post("/post")
        .then()
        .statusCode(200)
        .body("files.file", notNullValue());
  }

  /** Body from file: .body(new File(...)) or .body("classpath:data.json") */
  private static void demonstrateFileAndClasspathBody() {
    System.out.println("--- Classpath / file body ---");
    given()
        .baseUri("https://httpbin.org")
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/sample.json"))
        .post("/post")
        .then()
        .statusCode(200);

    // Classpath resource: pass stream or use File from src/test/resources in projects
    given()
        .baseUri("https://httpbin.org")
        .contentType(ContentType.JSON)
        .body(RequestBuildingRecap.class.getResourceAsStream("/sample.json"))
        .post("/post")
        .then()
        .statusCode(200);
  }
}
