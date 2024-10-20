package config;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public class RedirectConfigDemo {
    public static void main(String[] args) {

        // Redirect configuration can also be specified using the DSL. E.g.
        // We can use this to config number of max redirects and allows redirection to another server.
        RestAssured.config = RestAssuredConfig.config().redirect
                (RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(12)
                        .allowCircularRedirects(false));

        RequestSpecification requestSpecification = RestAssured.given().redirects().max(12).and().redirects().follow(true);
    }
}
