package config;

import io.restassured.RestAssured;
import io.restassured.config.SessionConfig;

public class SessionConfigDemo {
    public static void main(String[] args) {

        // With the session config you can configure the default session id name that's used by REST Assured.
        // The default session id name is JSESSIONID and you only need to change it
        // if the name in your application is different, and you want to make use of REST Assured's session support.

        RestAssured.config = RestAssured.config().sessionConfig(new SessionConfig().sessionIdName("phpsessionId"));
    }
}
