import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * REST Assured SME Recap: Serialization & Deserialization
 * 
 * Key Interview Points:
 * 1. Serialization: Converting Java Object (POJO/Map) -> JSON/XML.
 * 2. Deserialization: Converting JSON/XML -> Java Object.
 * 3. Default behavior: Rest Assured automatically serializes if Jackson/Gson is in classpath 
 *    and Content-Type is set.
 * 4. as() method: Used for deserialization (e.g., response.as(User.class)).
 */
public class SerializationDemo {

    @Test(description = "Serialization using Map")
    public void testMapSerialization() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Apple MacBook Pro 16");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2019);
        data.put("price", 1849.99);
        body.put("data", data);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Pro 16"));
    }

    /**
     * Interview Tip: How do you extract the response back into a POJO?
     */
    @Test(description = "Deserialization using .as() method")
    public void testDeserialization() {
        // String responseBody = get("/objects/1").asString(); // Not SME way
        
        // The SME Way:
        // ResponseModel model = get("/objects/1").as(ResponseModel.class);
        // System.out.println(model.getName());
        
        System.out.println("SME Tip: Use .as(Class) for full object mapping or .jsonPath().getObject(path, Class) for partial mapping.");
    }
}
