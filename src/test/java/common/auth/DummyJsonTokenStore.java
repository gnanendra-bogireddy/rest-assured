package common.auth;

import common.TestApis;
import io.restassured.http.ContentType;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.RestAssured.given;

/**
 * Holds OAuth2-style access + refresh tokens from DummyJSON.
 * Used by {@link filters.OAuth2TokenRefreshFilter} to attach Bearer auth and refresh on 401.
 */
public class DummyJsonTokenStore {

  private final AtomicReference<String> accessToken = new AtomicReference<>();
  private final AtomicReference<String> refreshToken = new AtomicReference<>();
  private volatile Instant expiresAt = Instant.EPOCH;

  /** Login and cache tokens (DummyJSON tokens are short-lived in real apps; we track expiry manually for demo). */
  public synchronized void login(String username, String password) {
    LoginResponse response =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .contentType(ContentType.JSON)
            .body(new LoginRequest(username, password))
            .post("/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .as(LoginResponse.class);

    accessToken.set(response.accessToken);
    refreshToken.set(response.refreshToken);
    // Demo: treat token as expired after 50 minutes (DummyJSON docs: ~60 min); force refresh in filter tests with markExpired()
    expiresAt = Instant.now().plusSeconds(50 * 60);
    System.out.println("[TokenStore] login ok user=" + response.username);
  }

  /** POST /auth/refresh — official DummyJSON refresh flow */
  public synchronized void refresh() {
    String currentRefresh = refreshToken.get();
    if (currentRefresh == null || currentRefresh.isBlank()) {
      login(TestApis.DUMMYJSON_USER, TestApis.DUMMYJSON_PASS);
      return;
    }

    LoginResponse response =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .contentType(ContentType.JSON)
            .body(new RefreshRequest(currentRefresh))
            .post("/auth/refresh")
            .then()
            .statusCode(200)
            .extract()
            .as(LoginResponse.class);

    accessToken.set(response.accessToken);
    refreshToken.set(response.refreshToken);
    expiresAt = Instant.now().plusSeconds(50 * 60);
    System.out.println("[TokenStore] refresh ok");
  }

  public String getAccessToken() {
    return accessToken.get();
  }

  public boolean isExpired() {
    return Instant.now().isAfter(expiresAt);
  }

  /** Test hook: local expiry flag — filter may proactive-refresh before call */
  public void markExpired() {
    expiresAt = Instant.EPOCH;
  }

  /** Force 401 from API to demo filter retry (invalid JWT) */
  public void forceInvalidAccessToken() {
    accessToken.set("invalid-access-token");
  }

  public String getRefreshToken() {
    return refreshToken.get();
  }

  /** Proactive refresh when local expiry flag set */
  public synchronized void refreshIfLocallyExpired() {
    if (isExpired()) {
      refresh();
    }
  }
}
