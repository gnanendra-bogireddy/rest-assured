package config;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;

public class LogConfigDemo {
    public static void main(String[] args) {

        // This will allow to config Rest Assured client Log configuration.
        // This will block the sensitive header like ETag and SessionId values logging to console.
        RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders());

        // This corresponding Header value will be replaced by BLOCKLISTED.
        RestAssured.config = RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Accept"));

        // This will be enabled Request and Response header if only validation fails.
        RestAssured.config = RestAssuredConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
