package config;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;

public class ConnectionConfigDemo {
    public static void main(String[] args) {

        // Lets you configure connection settings for REST Assured.
        // For example if you want to force-close the Apache HTTP Client connection after each response.
        // You may want to do this if you make a lot of fast consecutive requests
        // with small amount of data in the response. How ever if you're downloading (especially large amounts of)
        // chunked data you must not close connections after each response.
        // By default connections are not closed after each response.

        RestAssured.config = RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());


    }
}
