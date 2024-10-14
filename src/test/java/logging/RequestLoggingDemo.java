package logging;

import io.restassured.RestAssured;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

// Since version 1.5 REST Assured supports logging the request specification
// before it's sent to the server using the RequestLoggingFilter.
// Note that the HTTP Builder and HTTP Client may add additional headers
// then what's printed in the log. The filter will only log details specified
// in the request specification. I.e. you can NOT regard the details logged by
// the RequestLoggingFilter to be what's actually sent to the server.
// Also subsequent filters may alter the request after the logging has taken place.
// If you need to log what's actually sent on the wire refer to the HTTP Client logging
// docs or use an external tool such Wireshark.
public class RequestLoggingDemo {
    public static void main(String[] args) {

        RequestSpecification spec = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com")
                        .basePath("/comments").queryParam("postId", "1");
        System.out.println("****************** Printing all the logging details to console *******************");
        given().spec(spec).log().all().get(); // Log all request specification details including parameters, headers and body
        System.out.println("****************** Printing only the params details to console *******************");
        given().spec(spec).log().params().get(); // Log only the parameters of the request
        System.out.println("****************** Printing only the body details to console *******************");
        given().spec(spec).log().body().get(); // Log only the request body
        System.out.println("****************** Printing only the header details to console *******************");
        given().spec(spec).log().headers().get(); // Log only the request headers
        System.out.println("****************** Printing only the cookie details to console *******************");
        given().spec(spec).log().cookies().get(); // Log only the request cookies
        System.out.println("****************** Printing only the method details to console *******************");
        given().spec(spec).log().method().get(); // Log only the request method
        System.out.println("****************** Printing only the URI details to console *******************");
        given().spec(spec).log().uri().get(); // Log only the request URI
    }
}
