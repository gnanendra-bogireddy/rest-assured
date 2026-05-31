import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

/**
 * REST Assured SME Recap: Multi-part Requests (File Upload)
 * 
 * Key Interview Points:
 * 1. Multi-part: Used for uploading files along with other form data.
 * 2. Control Name: The field name expected by the server (e.g., "file", "image").
 * 3. MIME Type: Should match the file type (e.g., "image/jpeg", "application/pdf").
 */
public class MultiPartSpecDemo {

    @Test(description = "Demonstrating file upload using multiPart")
    public void testFileUpload() {
        // MultiPartSpecBuilder is useful for complex multi-part requests
        // File file = new File("path/to/file.jpg");

        RestAssured.given()
                .baseUri("https://httpbin.org")
                .contentType(ContentType.MULTIPART)
                // Simplest way:
                // .multiPart("file", file, "image/jpeg")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .log().all();
    }
}
