package response;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Response body access — when you need the payload outside Hamcrest {@code then()}.
 * <p>
 * {@link Response#asString()} — raw body
 * {@link Response#asPrettyString()} — formatted JSON/XML when applicable
 * {@link Response#asByteArray()} / {@link Response#asInputStream()} — binary downloads
 * {@link Response#as(Class)} — deserialize to POJO (Jackson on classpath)
 * {@link Response#prettyPeek()} / {@link Response#peek()} — log to console without failing test
 */
public class ResponseBodyDemo {

  public static void main(String[] args) {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    Response response = RestAssured.given().accept(ContentType.JSON).get("/posts/1");

    System.out.println("=== asString (first 80 chars) ===");
    String body = response.asString();
    System.out.println(body.substring(0, Math.min(80, body.length())) + "...");

    System.out.println("=== asPrettyString ===");
    System.out.println(response.asPrettyString());

    System.out.println("=== status & time ===");
    System.out.println("status: " + response.statusCode());
    System.out.println("time ms: " + response.time());

    System.out.println("=== peek (logs to console) ===");
    response.prettyPeek();

    RestAssured.reset();
  }
}
