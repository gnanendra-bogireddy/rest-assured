package config;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class EncodeConfigDemo {
    public static void main(String[] args) {

        EncoderConfig config = EncoderConfig.encoderConfig().defaultContentCharset("UTF-8");

        // With the EncoderConfig you can specify the default content encoding charset
        // (if it's not specified in the content-type header) and query parameter charset for all requests.
        // If no content charset is specified then ISO-8859-1 is used and if no query parameter
        // charset is specified then UTF-8 is used. Usage example

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("US-ASCII"));

        // You can also specify which encoder charset to use for a specific content-type
        // if no charset is defined explicitly for this content-type by using the defaultCharsetForContentType
        // method in the EncoderConfig. For example

        RestAssured.config = RestAssured.config.encoderConfig(encoderConfig().defaultCharsetForContentType("UTF-16", "application/xml"));
        // This will assume UTF-16 encoding for "application/xml" content-types that does explicitly specify a charset.
    }
}
