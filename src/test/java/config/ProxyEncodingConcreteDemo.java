package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.specification.ProxySpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.specification.ProxySpecification.host;
import static org.hamcrest.Matchers.equalTo;

/**
 * Proxy & URL encoding — REST Assured docs.
 * <p>
 * Proxy examples are shown as code patterns (require a live proxy on localhost to execute).
 * URL encoding demo uses httpbin.
 */
public class ProxyEncodingConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    // --- URL encoding (runnable) ---
    given()
        .baseUri(TestApis.HTTPBIN)
        .urlEncodingEnabled(true)
        .queryParam("city", "New York")
        .get("/get")
        .then()
        .body("args.city", equalTo("New York"));

    RestAssured.urlEncodingEnabled = false;

    // --- Proxy patterns (documented — enable when you have mitmproxy / corporate proxy) ---
    // RestAssured.proxy("127.0.0.1", 8888);
    // given().proxy(8888).get("https://example.com");
  ProxySpecification proxy =
        host("127.0.0.1").withPort(8888).withScheme("http").withAuth("user", "pass");
    System.out.println("Proxy spec example: " + proxy.getHost() + ":" + proxy.getPort());

    RestAssured.reset();
    System.out.println("ProxyEncodingConcreteDemo — done.");
  }
}
