package authentication;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

/**
 * REST Assured SME Recap: Authentication
 * 
 * Key Interview Points:
 * 1. Preemptive vs. Relaxed: Basic auth can be preemptive (send creds before challenge).
 * 2. OAuth 2.0: The industry standard for Bearer tokens.
 * 3. API Key: Can be sent in Header, Query Param, or Cookie.
 * 4. Auth Inheritance: Can be set globally or per request.
 */
public class BearerAuthDemo {

    @Test(description = "OAuth 2.0 Bearer Token Authentication")
    public void testBearerToken() {
        String token = "my_secret_token";

        // Modern way (Preferred for SME)
        RestAssured.given()
                .auth().oauth2(token)
                .when()
                .get("https://httpbin.org/bearer")
                .then()
                .statusCode(200);

        // Manual Header way (Sometimes needed for custom prefixes)
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("https://httpbin.org/bearer")
                .then()
                .statusCode(200);
    }
}
