package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

/**
 * SSL / HTTPS — REST Assured docs.
 * <ul>
 *   <li>{@link RestAssured#useRelaxedHTTPSValidation()} — trust self-signed / invalid certs (dev only)</li>
 *   <li>{@link SSLConfig#allowAllHostnames()} — skip hostname verification</li>
 *   <li>{@link SSLConfig#trustStore(File, String)} — custom truststore for corporate proxies</li>
 *   <li>{@link SSLConfig#keyStore(String, String)} — client certificate (mTLS)</li>
 * </ul>
 */
public class SslConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    // 1) Per-request relaxed HTTPS (public CA — works on jsonplaceholder)
    given().relaxedHTTPSValidation().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().statusCode(200);

    // 2) Global relaxed HTTPS
    RestAssured.useRelaxedHTTPSValidation();

    // 3) SSLConfig on RestAssured.config
    RestAssured.config =
        RestAssured.config()
            .sslConfig(SSLConfig.sslConfig().allowAllHostnames().relaxedHTTPSValidation());

    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().statusCode(200);

    // 4) trustStore / keystore — uncomment with real files in your environment:
    // RestAssured.config().sslConfig(SSLConfig.sslConfig().trustStore(new File("certs/trust.jks"), "changeit"));
    // RestAssured.config().sslConfig(SSLConfig.sslConfig().keyStore("certs/client.jks", "changeit"));

    RestAssured.reset();
    System.out.println("SslConfigConcreteDemo — done.");
  }
}
