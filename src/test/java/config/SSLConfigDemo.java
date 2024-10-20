package config;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import java.io.File;

public class SSLConfigDemo {
    public static void main(String[] args) {

        // The SSLConfig allows you to specify more advanced SSL configuration such as truststore,
        // keystore type and host name verifier. For example:
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().keystoreType("<type>").and().strictHostnames());

        // SSLConfig to allow requests for all host names.
        RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());

        // SSLConfig to load custom trust store.
        RestAssured.config().sslConfig(SSLConfig.sslConfig().trustStore(new File("file path"), "password"));

        // SSLConfig to avoid checking certificate and host name for requests.
        RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation("SSL/TLS"));

    }
}
