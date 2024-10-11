package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;

public class ErrorLoggingFilterDemo {
    // ErrorLoggingFilter is a class which extends StatusCodeBasedLoggingFilter which internally uses
    // Filter interface.- Filter interface has only one method filter and its return Response.
    // This will print response in console if any error occurs.
    public static void main(String[] args) {
        Filter filter = new ErrorLoggingFilter();

        RestAssured.baseURI = "https://httpbin.org";
        RestAssured.given()
                .filter(filter)
                .auth().none().pathParam("codes", 400)
                .get("/status/{codes}");
    }
}
