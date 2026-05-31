import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

/**
 * REST Assured SME Recap: Form-URL-Encoded Data
 * 
 * Key Interview Points:
 * 1. Content-Type: 'application/x-www-form-urlencoded'.
 * 2. formParam vs param: Use formParam specifically for form data. Rest Assured will 
 *    automatically set the content-type if you use formParam.
 * 3. Use Case: Legacy web forms and some OAuth 2.0 token endpoints.
 */
public class FormURLEncodedDemo {

    @Test(description = "POST request with Form Parameters")
    public void testFormUrlEncoded() {
        RestAssured.given()
                .baseUri("https://httpbin.org")
                .contentType(ContentType.URLENC) // application/x-www-form-urlencoded
                .formParam("custname", "John Doe")
                .formParam("size", "medium")
                .formParam("topping", "cheese", "mushroom") // Multi-value param
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .log().all();
    }
}
