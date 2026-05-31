package assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;

/**
 * REST Assured SME Recap: Assertions & Validation
 * 
 * Key Interview Points:
 * 1. Hamcrest Matchers: The core of Rest Assured assertions (equalTo, hasItem, containsString, etc.).
 * 2. Response Time: Crucial for performance testing (`.time(lessThan(2000L))`).
 * 3. Soft Assertions: Passing multiple body() calls in one then() block acts as a soft assertion 
 *    (it will try to validate all of them before failing).
 * 4. Header & Cookie Validation: `.header()` and `.cookie()`.
 */
public class AssertionsDemo {

    @Test(description = "Comprehensive Assertions Demo")
    public void testAllAssertions() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .header("Content-Encoding", "gzip")
                .time(lessThan(5L), SECONDS)
                .body("userId", equalTo(1))
                .body("title", is(notNullValue()))
                .body("body", containsString("quia et suscipi"));
    }

    @Test(description = "Validating Cookies")
    public void testCookieAssertions() {
        // RestAssured.get("/x").then().assertThat().cookie("name", "value");
    }
}
