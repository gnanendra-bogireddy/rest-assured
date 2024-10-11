package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class ResponseLoggingFilterDemo {
    // ResponseLoggingFilter is a class which extends StatusCodeBasedLoggingFilter class which implements Filter interface,
    // Filter interface has only one method filter and its return Response.
    // This will print response in console.
    public static void main(String[] args) {
        Filter filter = new ResponseLoggingFilter();

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.given()
                .filter(filter)
                .auth().none()
                .get("/posts");
    }
}
