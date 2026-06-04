package recap;

import static io.restassured.RestAssured.given;

/**
 * Authentication shortcuts on {@code given().auth()}.
 * <p>
 * <ul>
 *   <li>{@code basic(user, pass)} — HTTP Basic; add preemptive for no 401 round-trip</li>
 *   <li>{@code digest(user, pass)} — HTTP Digest</li>
 *   <li>{@code oauth2(token)} — Bearer token (OAuth 2.0 style)</li>
 *   <li>{@code oauth(...)} — OAuth 1.0 (requires scribejava)</li>
 *   <li>{@code none()} — explicit no auth</li>
 * </ul>
 * API keys: usually {@code .header("api-key", ...)} or {@code .queryParam("key", ...)}.
 */
public class AuthenticationRecap {

  public static void main(String[] args) {
    demonstrateBasicAuth();
    demonstrateBearerToken();
    demonstratePreemptiveBasic();
    System.out.println("AuthenticationRecap — done.");
  }

  private static void demonstrateBasicAuth() {
    System.out.println("--- Basic auth ---");
    given()
        .baseUri("https://httpbin.org")
        .auth()
        .basic("user", "passwd")
        .get("/basic-auth/user/passwd")
        .then()
        .statusCode(200);
  }

  private static void demonstrateBearerToken() {
    System.out.println("--- OAuth2 Bearer ---");
    given().auth().oauth2("test-token").get("https://httpbin.org/bearer").then().statusCode(200);
  }

  private static void demonstratePreemptiveBasic() {
    System.out.println("--- Preemptive basic ---");
    given()
        .baseUri("https://httpbin.org")
        .auth()
        .preemptive()
        .basic("user", "passwd")
        .get("/basic-auth/user/passwd")
        .then()
        .statusCode(200);
  }
}
