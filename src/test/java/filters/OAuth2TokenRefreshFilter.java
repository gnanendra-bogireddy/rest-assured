package filters;

import common.auth.DummyJsonTokenStore;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Custom filter: attach Bearer token; on HTTP 401 refresh token once and retry.
 * <p>
 * Pattern from enterprise API tests — centralize token lifecycle instead of duplicating in each test.
 * Uses {@link DummyJsonTokenStore} (DummyJSON {@code /auth/login} + {@code /auth/refresh}).
 */
public class OAuth2TokenRefreshFilter implements Filter {

  private final DummyJsonTokenStore tokenStore;
  private final String headerName;

  public OAuth2TokenRefreshFilter(DummyJsonTokenStore tokenStore) {
    this(tokenStore, "Authorization");
  }

  public OAuth2TokenRefreshFilter(DummyJsonTokenStore tokenStore, String headerName) {
    this.tokenStore = tokenStore;
    this.headerName = headerName;
  }

  @Override
  public Response filter(
      FilterableRequestSpecification requestSpec,
      FilterableResponseSpecification responseSpec,
      FilterContext ctx) {

    tokenStore.refreshIfLocallyExpired();
    attachBearer(requestSpec);

    Response response = ctx.next(requestSpec, responseSpec);

    if (response != null && response.statusCode() == 401) {
      System.out.println("[OAuth2Filter] 401 — refreshing token and retrying once");
      tokenStore.refresh();
      requestSpec.removeHeader(headerName);
      attachBearer(requestSpec);
      Response retry = ctx.next(requestSpec, responseSpec);
      if (retry != null) {
        response = retry;
      }
    }

    return response;
  }

  private void attachBearer(FilterableRequestSpecification requestSpec) {
    String token = tokenStore.getAccessToken();
    if (token != null) {
      requestSpec.header(headerName, "Bearer " + token);
    }
  }
}
