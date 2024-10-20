import io.restassured.RestAssured;
import io.restassured.specification.ProxySpecification;

import static io.restassured.specification.ProxySpecification.host;

public class ProxyandEncodingDemo {
    public static void main(String[] args) {

        // Proxy configuration without Auth is below. It has so many overloaded methods.
        RestAssured.proxy("localhost", 8080, "https");
        RestAssured.given().proxy(8888); // Will assume localhost

        // Proxy with auth method is given below.
        RestAssured.given().proxy(new ProxySpecification("localhost", 8080, "https")
                .withAuth("username", "password")).get("/path").then().log().all();

        RestAssured.given().proxy(host("http://myhost.org").withAuth("username", "password"));

        // Encoder config - Sometimes we need to disable encoder config not to encode twice.

        RestAssured.given().urlEncodingEnabled(false).get("https://myhost.org/v1/something");

        // Globally declare to disable to encode - This will disable encoding for subsequent requests also.
        RestAssured.urlEncodingEnabled = false;
    }
}
