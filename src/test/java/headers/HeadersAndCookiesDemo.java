package headers;

import common.TestApis;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Request/response headers and cookies — per REST Assured documentation.
 */
public class HeadersAndCookiesDemo {

  public static void main(String[] args) {
    requestHeaders();
    responseHeaders();
    cookiesFlow();
    sessionId();
    System.out.println("HeadersAndCookiesDemo — done.");
  }

  static void requestHeaders() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .header("X-Api-Version", "1")
        .headers("Accept", "application/json", "X-Trace-Id", "trace-abc")
        .get("/headers")
        .then()
        .statusCode(200)
        .body("headers.X-Api-Version", equalTo("1"));
  }

  static void responseHeaders() {
    Response response = given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1");

    Headers headers = response.getHeaders();
    String contentType = response.getContentType();
    System.out.println("Response header count: " + headers.size());
    System.out.println("Content-Type: " + contentType);

    response.then().header("Content-Type", containsString("application/json"));
  }

  static void cookiesFlow() {
    Response set = given().baseUri(TestApis.HTTPBIN).get("/cookies/set?token=xyz&role=admin");

    Cookies cookies = set.detailedCookies();
    given()
        .baseUri(TestApis.HTTPBIN)
        .cookies(cookies)
        .get("/cookies")
        .then()
        .cookie("token", equalTo("xyz"))
        .cookie("role", equalTo("admin"));
  }

  /** sessionId uses SessionConfig default name JSESSIONID */
  static void sessionId() {
    given().baseUri(TestApis.HTTPBIN).sessionId("demo-session").get("/cookies").then().statusCode(200);
  }
}
