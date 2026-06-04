package builders;

import common.TestApis;
import io.restassured.builder.MultiPartSpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

/**
 * MultiPartSpecBuilder — control name, filename, mime type, charset.
 */
public class MultiPartSpecBuilderConcreteDemo {

  public static void main(String[] args) {
    byte[] data = "builder multipart".getBytes();

    given()
        .baseUri(TestApis.HTTPBIN)
        .multiPart(
            new MultiPartSpecBuilder(data)
                .fileName("data.txt")
                .controlName("upload")
                .mimeType("text/plain")
                .build())
        .post("/post")
        .then()
        .statusCode(200)
        .body("files.upload", notNullValue());

    System.out.println("MultiPartSpecBuilderConcreteDemo — done.");
  }
}
