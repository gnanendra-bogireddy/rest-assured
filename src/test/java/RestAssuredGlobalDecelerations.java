import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.JsonConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

public class RestAssuredGlobalDecelerations {
// This file consists of how to declare global constants in Rest Assured.
    public static void main(String[] args) {
        // If we want to skip the validation of certificate of the server,
        // we can use useRelaxedHTTPSValidation() method. This is applicable for entire session.
        RestAssured.useRelaxedHTTPSValidation();

        // This is a overloaded method of above one to specify protocol.
        RestAssured.useRelaxedHTTPSValidation("SSL");

        // To rest all the defined values to default values use reset() method.
        RestAssured.reset();

        // To define baseURI for entire session.
        RestAssured.baseURI = "https://localhost";

        // To define port number for entire session.
        RestAssured.port = 8080;

        // To define basePath for entire session.
        RestAssured.basePath = "/path";

        // To define rootPath for entire session.
        RestAssured.rootPath = "x.y.z";

        // To define sessionId for entire session. This may be bad idea to declare a constant values for sessionId.
        RestAssured.sessionId = "JSESSIONID";

        // declaring JSON as a default parser for entire session.
        RestAssured.defaultParser = Parser.JSON;

        RequestSpecification requestSpecification = RestAssured.given()
                .auth().none()
                .param("key", "value")
                .header("Key", "Value")
                .request();

        // defines request specification globally.
        RestAssured.requestSpecification = requestSpecification;

        ResponseSpecification responseSpecification = RestAssured.expect()
                .header("Key", "Value")
                .contentType(ContentType.JSON)
                .statusCode(200)
                .statusLine("200 OK");

        // defines response specification globally.
        RestAssured.responseSpecification = responseSpecification;

        // To define authentication globally for entire session.
        RestAssured.authentication = RestAssured.basic("username", "password");

        // To enable or disable decoding for REST requests globally.
        RestAssured.urlEncodingEnabled = true;

        // To provide proxy server to REST requests globally.
        RestAssured.proxy = new ProxySpecification("host", 8080, "https");

        // To define RestAssured config globally use the below static variable.
        RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders())
                .sslConfig(SSLConfig.sslConfig().allowAllHostnames());
    }
}
