package config;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

import static io.restassured.config.DecoderConfig.ContentDecoder.DEFLATE;
import static io.restassured.config.DecoderConfig.ContentDecoder.GZIP;
import static io.restassured.config.DecoderConfig.decoderConfig;

public class DecoderConfigDemo {
    // Detailed configuration is provided by the RestAssuredConfig instance with which you can configure
    // the parameters of HTTP Client as well as Redirect, Log, Encoder, Decoder, Session, ObjectMapper,
    // Connection, SSL and ParamConfig settings.

    // With the DecoderConfig you can set the default response content decoding charset for all responses.
    // This is useful if you expect a different content charset than ISO-8859-1 (which is the default charset)
    // and the response doesn't define the charset in the content-type header.
    public static void main(String[] args) {

        DecoderConfig config = DecoderConfig.decoderConfig().defaultContentCharset("UTF-16");

        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));

        // You can also use the DecoderConfig to specify which content decoders to apply.
        // When you do this the Accept-Encoding header will be added automatically to the request
        // and the response body will be decoded automatically. By default GZIP and DEFLATE decoders are enabled.
        // To for example to remove GZIP decoding but retain DEFLATE decoding you can do the following:
        RestAssured.given().config(RestAssured.config().decoderConfig(decoderConfig().contentDecoders(DEFLATE)));

        // Below is the code for GZIP decoding.
        RestAssured.given().config(RestAssuredConfig.config().decoderConfig(decoderConfig().contentDecoders(GZIP)));

        //You can also specify which decoder charset to use for a specific content-type
        // if no charset is defined explicitly for this content-type by using the "defaultCharsetForContentType"
        // method in the DecoderConfig. For example:
        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultCharsetForContentType("UTF-16", "application/xml"));
        //This will assume UTF-16 encoding for "application/xml" content-types that does explicitly specify a charset.

    }
}
