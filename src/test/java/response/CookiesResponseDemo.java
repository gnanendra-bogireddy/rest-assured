package response;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Cookies — set on request with {@code .cookie()}, read from response via {@code .cookies()} or {@code .detailedCookies()}.
 * <p>
 * {@code .sessionId("value")} uses configured session id name (default JSESSIONID) for stateful flows.
 */
public class CookiesResponseDemo {

  public static void main(String[] args) {
    // Server sets cookie
    Response setCookie =
        given().baseUri("https://httpbin.org").get("/cookies/set?session=interview&theme=dark");

    Cookies cookies = setCookie.detailedCookies();
    System.out.println("Cookies from Set-Cookie:");
    for (Cookie c : cookies) {
      System.out.printf("  %s=%s domain=%s path=%s%n", c.getName(), c.getValue(), c.getDomain(), c.getPath());
    }

    // Send cookies on next request
    given()
        .baseUri("https://httpbin.org")
        .cookies(cookies)
        .get("/cookies")
        .then()
        .statusCode(200)
        .cookie("session", equalTo("interview"))
        .cookie("theme", equalTo("dark"));

    // Session id shorthand
    given().baseUri("https://httpbin.org").sessionId("abc").get("/cookies").then().statusCode(200);

    RestAssured.reset();
  }
}
