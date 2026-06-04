package recap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.ResponseModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

/**
 * Working with {@link Response} — extract data for reuse, logging, or downstream calls.
 * <p>
 * <b>Two styles</b>
 * <ol>
 *   <li><b>Validate inline</b>: {@code .then().body(...)} — fail fast in test</li>
 *   <li><b>Extract then assert in Java</b>: {@code .extract().path("id")} — flexible for chaining</li>
 * </ol>
 * <b>JsonPath</b> is bundled; {@code $} root prefix is optional in recent versions.
 * Groovy GPath ({@code find { it.id == 1 }}) works in {@code .body()} and {@code jsonPath().get()}.
 */
public class ResponseExtractionRecap {

  public static void main(String[] args) {
    demonstrateExtractApi();
    demonstrateResponseAsMethods();
    demonstrateJsonPathExtraction();
    demonstratePojoMapping();
    demonstrateChainedRequests();
    System.out.println("ResponseExtractionRecap — done.");
  }

  private static void demonstrateExtractApi() {
    System.out.println("--- extract() API ---");
    ExtractableResponse<Response> extracted =
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .get("/posts/1")
            .then()
            .statusCode(200)
            .extract();

    int id = extracted.path("id");
    String title = extracted.path("title");
    int status = extracted.statusCode();
    String contentType = extracted.contentType();
    System.out.printf("extracted: id=%d title=%s status=%d type=%s%n", id, title, status, contentType);

    // Shorthand without storing ExtractableResponse
    int userId =
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .get("/posts/1")
            .jsonPath()
            .getInt("userId");
    System.out.println("userId via jsonPath(): " + userId);
  }

  private static void demonstrateResponseAsMethods() {
    System.out.println("--- Response body as* ---");
    Response response =
        given().baseUri("https://jsonplaceholder.typicode.com").get("/posts/1");

    String raw = response.asString();
    String pretty = response.asPrettyString();
    byte[] bytes = response.asByteArray();
    JsonPath jp = response.jsonPath();

    System.out.println("body length: " + raw.length());
    System.out.println("pretty starts with: " + pretty.substring(0, Math.min(40, pretty.length())) + "...");
    System.out.println("bytes: " + bytes.length);
    System.out.println("jsonPath title: " + jp.getString("title"));

    // Peek without assertions
    response.prettyPeek();
  }

  private static void demonstrateJsonPathExtraction() {
    System.out.println("--- JsonPath list & Groovy ---");
    Response response =
        given().baseUri("https://jsonplaceholder.typicode.com").get("/users");

    List<String> emails = response.jsonPath().getList("email");
    String fifthName = response.jsonPath().getString("[4].name");
    String city = response.jsonPath().getString("find { it.id == 1 }.address.city");

    System.out.println("email count: " + emails.size());
    System.out.println("5th user: " + fifthName);
    System.out.println("user 1 city: " + city);
  }

  private static void demonstratePojoMapping() {
    System.out.println("--- POJO deserialization ---");
    RestAssured.reset();
    Response response =
        given()
            .baseUri("https://api.restful-api.dev")
            .accept(ContentType.JSON)
            .get("/objects/5")
            .then()
            .statusCode(200)
            .extract()
            .response();

    ResponseModel model = response.as(ResponseModel.class);
    System.out.println("POJO name: " + model.name);
    if (model.data != null) {
      System.out.println("POJO data year: " + model.data.year);
    }

    List<ResponseModel> all =
        given()
            .baseUri("https://api.restful-api.dev")
            .get("/objects")
            .jsonPath()
            .getList("$", ResponseModel.class);
    System.out.println("list size: " + all.size());
  }

  /**
   * Interview pattern: call A, extract value, call B — cannot do with only {@code then()} Hamcrest.
   * Note: jsonplaceholder does not persist POSTs; we extract {@code userId} from POST and filter GET list.
   */
  private static void demonstrateChainedRequests() {
    System.out.println("--- Chained requests ---");
    int userId =
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType(ContentType.JSON)
            .body("{\"title\":\"chain\",\"body\":\"b\",\"userId\":1}")
            .post("/posts")
            .then()
            .statusCode(201)
            .extract()
            .path("userId");

    ValidatableResponse second =
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .queryParam("userId", userId)
            .get("/posts")
            .then()
            .statusCode(200)
            .body("userId", everyItem(equalTo(userId)));

    second.log().ifValidationFails();
    System.out.println("chained userId: " + userId);
  }
}
