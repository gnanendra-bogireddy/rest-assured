package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.filter.time.TimingFilter;
import io.restassured.http.ContentType;

public class MultipleFiltersDemo {
    // Filter interface has only one method filter and its return Response.
    // This will print request and response in console.
    // CookieFilter will print Cookies information in console.
    public static void main(String[] args) {
        Filter requestFilter = new RequestLoggingFilter();
        Filter resposeFilter = new ResponseLoggingFilter();
        Filter cookieFilter = new CookieFilter();
        Filter timingFilter = new TimingFilter();
        Filter sessionFilter = new SessionFilter();

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.given()
                .filters(requestFilter, resposeFilter,cookieFilter,timingFilter,sessionFilter)
                .contentType(ContentType.JSON)
                .cookie("name", "value")
                .auth().none()
                .queryParam("postId", "1")
                .get("/comments");
    }
}
