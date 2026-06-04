package filters;

import common.TestApis;
import common.auth.DummyJsonTokenStore;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Runnable demo: OAuth2 Bearer via custom filter with automatic refresh on 401.
 */
public class OAuth2TokenRefreshFilterDemo {

  public static void main(String[] args) {
    DummyJsonTokenStore tokenStore = new DummyJsonTokenStore();
    tokenStore.login(TestApis.DUMMYJSON_USER, TestApis.DUMMYJSON_PASS);

    OAuth2TokenRefreshFilter authFilter = new OAuth2TokenRefreshFilter(tokenStore);

    // Protected route — requires Bearer (DummyJSON /auth/me)
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .filter(authFilter)
        .accept(ContentType.JSON)
        .get("/auth/me")
        .then()
        .statusCode(200)
        .body("username", equalTo(TestApis.DUMMYJSON_USER));

    // Proactive refresh when local expiry flag is set (before request is sent)
    tokenStore.markExpired();
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .filter(authFilter)
        .get("/auth/me")
        .then()
        .statusCode(200);

    // After invalid token: refresh store then call again (filter re-attaches Bearer)
    tokenStore.forceInvalidAccessToken();
    tokenStore.refresh();
    given()
        .baseUri(TestApis.DUMMY_JSON)
        .filter(authFilter)
        .get("/auth/me")
        .then()
        .statusCode(200);

    System.out.println("OAuth2TokenRefreshFilterDemo — passed.");
  }
}
