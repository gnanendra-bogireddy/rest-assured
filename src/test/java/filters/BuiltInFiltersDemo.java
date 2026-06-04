package filters;

import common.TestApis;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.filter.time.TimingFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Built-in filters: CookieFilter, SessionFilter, TimingFilter (+ logging filters).
 */
public class BuiltInFiltersDemo {

  public static void main(String[] args) {
    CookieFilter cookieFilter = new CookieFilter();
    SessionFilter sessionFilter = new SessionFilter();
    TimingFilter timingFilter = new TimingFilter();

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .filters(
            new RequestLoggingFilter(),
            new ResponseLoggingFilter(),
            cookieFilter,
            sessionFilter,
            timingFilter)
        .contentType(ContentType.JSON)
        .queryParam("postId", 1)
        .get("/comments")
        .then()
        .statusCode(200)
        .body("postId", everyItem(equalTo(1)));

    System.out.println("BuiltInFiltersDemo — done.");
  }
}
