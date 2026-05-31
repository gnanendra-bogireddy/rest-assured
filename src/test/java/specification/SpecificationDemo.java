package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

/**
 * REST Assured SME Recap: Specifications (Reusability & Clean Code)
 * 
 * Key Interview Points:
 * 1. RequestSpecBuilder: Used to create reusable request templates.
 * 2. ResponseSpecBuilder: Used to create reusable response validation templates.
 * 3. Why use them? 
 *    - DRÝ (Don't Repeat Yourself)
 *    - Centralized configuration (e.g., change base URL in one place).
 *    - Thread Safety: Better than using RestAssured static fields for parallel tests.
 */
public class SpecificationDemo {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        // Build Request Specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .addHeader("X-API-VERSION", "2.0")
                .build();

        // Build Response Specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test(description = "Using specs for cleaner tests")
    public void testWithSpecs() {
        RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .body("id", equalTo(1));
    }

    @Test(description = "Overriding spec values")
    public void testOverridingSpec() {
        RestAssured.given()
                .spec(requestSpec)
                .basePath("/comments") // Override or add to spec
                .queryParam("postId", 1)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .body("size()", equalTo(5));
    }
}
