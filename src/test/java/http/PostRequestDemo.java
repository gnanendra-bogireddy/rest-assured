package http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * REST Assured SME Recap: POST Requests & Body Payload
 * 
 * Key Interview Points:
 * 1. Payload formats: String, Map, External File, POJO.
 * 2. Serialization: Converting Java objects to JSON (Rest Assured uses Jackson/Gson).
 * 3. Content-Type: Must be set to 'application/json' for most modern APIs.
 */
public class PostRequestDemo {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "POST request using String Body (Hardcoded)")
    public void testPostWithStringBody() {
        String payload = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test(description = "POST request using Map (Dynamic Payload)")
    public void testPostWithMapBody() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "SME Recap");
        payload.put("body", "REST Assured is powerful");
        payload.put("userId", 101);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("SME Recap"))
                .body("userId", equalTo(101));
    }

    /**
     * Interview Tip: 
     * POJO (Plain Old Java Object) is the best practice for complex payloads 
     * as it supports type safety and reusability.
     * 
     * Requires Jackson or Gson in the classpath.
     */
    @Test(description = "POST request using POJO")
    public void testPostWithPojo() {
        // Assuming we have a model class. For demo, we'll use a local class or just describe it.
        // PostModel post = new PostModel("title", "body", 1);
        
        // RestAssured.given()
        //        .contentType(ContentType.JSON)
        //        .body(post)
        //        .post("/posts")
        //        ...
    }
}
