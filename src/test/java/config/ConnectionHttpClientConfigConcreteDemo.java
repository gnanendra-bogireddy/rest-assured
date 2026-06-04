package config;

import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ParamConfig;

import static io.restassured.RestAssured.given;

/**
 * ConnectionConfig & HttpClientConfig — timeouts, idle connections, HTTP client params.
 */
public class ConnectionHttpClientConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .connectionConfig(
                ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse())
            .httpClient(
                HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 10_000)
                    .setParam("http.socket.timeout", 10_000))
            .paramConfig(ParamConfig.paramConfig().queryParamsUpdateStrategy(ParamConfig.UpdateStrategy.REPLACE));

    given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().statusCode(200);

    RestAssured.reset();
    System.out.println("ConnectionHttpClientConfigConcreteDemo — done.");
  }
}
