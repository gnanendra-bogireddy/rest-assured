package parsing;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * REST Assured SME Recap: Advanced JsonPath & Groovy Expressions
 * 
 * Key Interview Points:
 * 1. Groovy GPath: Rest Assured uses Groovy's GPath notation for complex queries.
 * 2. Find/FindAll: powerful for filtering lists based on criteria.
 * 3. Max/Min/Sum: Built-in Groovy functions for data analysis.
 * 4. it: Refers to the current element in the list during iteration.
 */
public class JsonPathAdvancedDemo {

    @Test(description = "Advanced JSON parsing using Groovy expressions")
    public void testAdvancedParsing() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        JsonPath jsonPath = response.jsonPath();

        // 1. Find a single user by name
        String name = jsonPath.getString("find { it.id == 5 }.name");
        System.out.println("User with ID 5: " + name);

        // 2. Find all users living in a specific city
        List<String> namesInCity = jsonPath.getList("findAll { it.address.city == 'Gwenborough' }.name");
        System.out.println("Users in Gwenborough: " + namesInCity);

        // 3. Collect all usernames
        List<String> usernames = jsonPath.getList("username");
        System.out.println("All Usernames: " + usernames);

        // 4. Using collect to transform data
        List<String> emails = jsonPath.getList("collect { it.email }");

        // 5. Max/Min
        // String maxIdUser = jsonPath.getString("max { it.id }.name");
    }

    @Test(description = "Validating list size and content using Hamcrest")
    public void testListAssertions() {
        RestAssured.given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("size()", is(100))
                .body("id", hasItems(1, 2, 3))
                .body("findAll { it.userId == 1 }.size()", equalTo(10));
    }
}
