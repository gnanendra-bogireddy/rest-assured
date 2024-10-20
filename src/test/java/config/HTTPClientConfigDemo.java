package config;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;

public class HTTPClientConfigDemo {
    public static void main(String[] args) {

        // By default, Rest Assured will create a new Http Client instance, If you want to re-use instance again
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().reuseHttpClientInstance());

        // You can also supply a custom HTTP Client instance by using the httpClientFactory method, for example:
        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().httpClientFactory(
                new HttpClientConfig.HttpClientFactory() {

                    @Override
                    public HttpClient createHttpClient() {
                        return new SystemDefaultHttpClient();
                    }
                }));
        // Note that currently you need to supply an instance of AbstractHttpClient.

        // By default, Rest Assured will create a new Http Client instance, If you want to create a new Http Client
        // for every request use the below method.
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig().dontReuseHttpClientInstance());

    }
}
