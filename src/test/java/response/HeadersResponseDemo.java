package response;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * Response headers — read and assert.
 * <p>
 * {@code response.getHeaders()} / {@code getHeader("Name")} / {@code getContentType()}
 * In {@code then()}: {@code .header("X", "value")} or Hamcrest {@code containsString}.
 */
public class HeadersResponseDemo {

  public static void main(String[] args) {
    Response response =
        RestAssured.given()
            .baseUri("https://httpbin.org")
            .header("X-Request-Id", "demo-123")
            .get("/get");

    Headers headers = response.getHeaders();
    String contentType = response.getContentType();
    System.out.println("Content-Type: " + contentType);
    System.out.println("Header count: " + headers.size());
    headers.asList().forEach(h -> System.out.println("  " + h.getName() + ": " + h.getValue()));

    response
        .then()
        .statusCode(200)
        .header("Content-Type", org.hamcrest.Matchers.containsString("application/json"));

  }
}
