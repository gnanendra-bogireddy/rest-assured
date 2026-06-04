package filters;

import common.TestApis;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Filters — built-in logging + custom + OAuth2 refresh (see {@link OAuth2TokenRefreshFilterDemo}).
 * <p>
 * Order: filters run in registration order; each calls {@code ctx.next()} to continue chain.
 */
public class AllFiltersDemo {

  public static void main(String[] args) {
    requestAndResponseLoggingFilters();
    errorLoggingFilterOn4xx();
    customCorrelationIdFilter();
    multipleFiltersCombined();
    System.out.println("AllFiltersDemo — done.");
  }

  static void requestAndResponseLoggingFilters() {
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
        .get("/posts/1")
        .then()
        .statusCode(200);
  }

  /** Logs response body when status is 4xx or 5xx */
  static void errorLoggingFilterOn4xx() {
    given()
        .baseUri(TestApis.HTTPBIN)
        .filter(new ErrorLoggingFilter())
        .get("/status/404")
        .then()
        .statusCode(404);
  }

  static void customCorrelationIdFilter() {
    Filter correlationFilter =
        (FilterableRequestSpecification req,
            FilterableResponseSpecification res,
            FilterContext ctx) -> {
          req.header("X-Correlation-Id", "corr-" + System.currentTimeMillis());
          Response response = ctx.next(req, res);
          System.out.println("[CustomFilter] " + req.getMethod() + " " + req.getURI() + " → " + response.statusCode());
          return response;
        };

    given()
        .baseUri(TestApis.HTTPBIN)
        .filter(correlationFilter)
        .get("/get")
        .then()
        .body("headers.X-Correlation-Id", org.hamcrest.Matchers.notNullValue());
  }

  static void multipleFiltersCombined() {
    Filter assertOk =
        (req, res, ctx) -> {
          Response r = ctx.next(req, res);
          if (r.statusCode() >= 400) {
            System.err.println("Unexpected error status: " + r.statusCode());
          }
          return r;
        };

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .filter(new RequestLoggingFilter())
        .filter(assertOk)
        .filter(new ErrorLoggingFilter())
        .get("/posts/1")
        .then()
        .statusCode(equalTo(200));
  }
}
