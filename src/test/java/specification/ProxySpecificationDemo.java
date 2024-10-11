package specification;

import io.restassured.RestAssured;
import io.restassured.specification.ProxySpecification;

public class ProxySpecificationDemo {
    public static void main(String[] args) {

        ProxySpecification proxySpecification = new ProxySpecification("host", 8080, "https");
        proxySpecification.withAuth("username", "password");
        RestAssured.proxy = proxySpecification;
    }
}
