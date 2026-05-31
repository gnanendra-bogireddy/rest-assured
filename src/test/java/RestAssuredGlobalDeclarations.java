import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

/**
 * REST Assured SME Recap: Global Configurations
 * 
 * Key Interview Points:
 * 1. Global Setup: How to avoid redundancy by setting baseURI, port, etc., globally.
 * 2. Static Imports: Rest Assured relies heavily on static methods for DSL.
 * 3. Thread Safety: RestAssured static fields are NOT thread-safe for concurrent execution 
 *    with different configs. Use Request/Response Specifications for thread-safe parallel testing.
 * 4. Relaxed HTTPS: Essential for testing in dev/stage environments with self-signed certificates.
 */
public class RestAssuredGlobalDeclarations {

    @BeforeSuite
    public void setup() {
        // --- 1. Basic Connection Settings ---
        RestAssured.baseURI = "https://api.example.com";
        RestAssured.port = 443;
        RestAssured.basePath = "/v1";

        // --- 2. Security & SSL ---
        // Skip certificate validation (Self-signed certs)
        RestAssured.useRelaxedHTTPSValidation();
        // Specific protocol can be defined: RestAssured.useRelaxedHTTPSValidation("TLS");

        // --- 3. Global Specifications ---
        // Note: In SME frameworks, we prefer passing these to .spec() rather than global assignment
        // to ensure thread safety if running tests in parallel.
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("X-Request-ID", "SME-RECAP")
                .build();
        
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;

        // --- 4. Advanced Configuration ---
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
                .sslConfig(SSLConfig.sslConfig().allowAllHostnames());

        // --- 5. Default Parsing ---
        // Useful if the server returns content-type that Rest Assured doesn't recognize automatically
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void demonstrationTest() {
        // This test will use all the global settings defined above
        RestAssured.given()
                .get("/health")
                .then()
                .statusCode(200);
    }

    /**
     * Interview Tip: How do you reset Rest Assured to its default state?
     */
    public void cleanup() {
        RestAssured.reset();
    }
}
