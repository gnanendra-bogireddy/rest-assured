package logging;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;

public class ResponseLoggingDemo {
    public static void main(String[] args) {

        // If you want to print the response body regardless of the status code you can do
        System.out.println("************************** Printing response body **************************");
        given().get("https://jsonplaceholder.typicode.com/posts").then().log().body();

        // This will print the response body regardless if an error occurred. If you're only interested in printing
        // the response body if an error occur then you can use
        System.out.println("************************** Printing response only after error occurs **************************");
        given().get("https://httpbin.org/status/401").then().log().ifError();

        // You can also log all details in the response including status line, headers and cookies
        System.out.println("************************** Printing response body, headers, cookies **************************");
        given().get("https://jsonplaceholder.typicode.com/posts").then().log().all();

        // as well as only status line, headers or cookies
        System.out.println("************************** Printing only response status **************************");
        given().get("https://httpbin.org/get").then().log().status(); // Only log the status line
        System.out.println("************************** Printing only response header **************************");
        given().get("https://httpbin.org/get").then().log().headers(); // Only log the response headers
        System.out.println("************************** Printing only response cookies **************************");
        given().get("https://postman-echo.com/cookies/set?foo1=bar1&foo2=bar2").then().log().cookies(); // Only log the response cookies

        // You can also configure to log the response only if the status code matches some value
        System.out.println("************************** Printing only response code is equal to given value **************************");
        given().get("https://httpbin.org/get").then().log().ifStatusCodeIsEqualTo(200); // Only log if the status code is equal to 302

        // Since REST Assured 2.3.1 you can log the request or response only if the validation fails. To log the request do
        given().log().ifValidationFails();

        //It's also possible to enable this for both the request and the response at the same time using the LogConfig
        given().config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()));

        // As of REST Assured 4.2.0 it's possible to blacklist headers so that they are not shown
        // in the request or response log. Instead, the header value will be replaced with [ BLACKLISTED ].
        // You can enable this per header basis using the LogConfig
        System.out.println("************************** Printing response after blocklisting header **************************");
        given().config(config().logConfig(logConfig().blacklistHeader("Content-Type")))
                .get("https://httpbin.org/get").then().log().headers();

    }
}
