package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;

public class ErrorLoggingFilterDemo {
    public static void main(String[] args) {
        Filter filter = new ErrorLoggingFilter();

        RestAssured.baseURI = "https://httpbin.org";

        RestAssured.given()
                .filter(filter)
                .auth().none().pathParam("codes", 400)
                .get("/status/{codes}");

    }
}
