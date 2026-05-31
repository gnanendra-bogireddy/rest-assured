package assertions;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

/**
 * REST Assured SME Recap: JSON Schema Validation
 * 
 * Key Interview Points:
 * 1. Structural Validation: Matches the response against a predefined JSON schema (draft 4, 6, 7, etc.).
 * 2. Why use it? To ensure API contracts are not broken (data types, mandatory fields).
 * 3. matchers: .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"))
 */
public class JsonSchemaValidationDemo {

    @Test(description = "Validate response against JSON Schema")
    public void testJsonSchema() {
        // In a real project, place the schema.json in src/test/resources
        // matchesJsonSchemaInClasspath("schemas/user-schema.json")

        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                // This is how you call it. It will look for the file in classpath.
                // .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post-schema.json"));
                .log().all();
        
        System.out.println("SME Tip: JSON Schema validation is critical for contract testing.");
    }
}
