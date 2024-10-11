package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;

public class RequestLoggingFilterDemo {
    // RequestLoggingFilter is a class which implements Filter interface,
    // Filter interface has only one method filter and its return Response.
    // This will print request information in console.
    public static void main(String[] args) {
        Filter filter = new RequestLoggingFilter();

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.given()
                .filter(filter)
                .contentType(ContentType.JSON)
                .cookie("name", "value")
                .auth().none()
                .queryParam("postId", "1")
                .get("/comments");

        RestAssured.given()
                .filter(filter)
                .auth().none()
                .pathParam("id",1)
                .get("/posts/{id}");
    }
}
