package upload;

import common.TestApis;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Multipart file upload & binary body (REST Assured docs: multiPart, binary body).
 */
public class MultiPartAndBinaryDemo {

  public static void main(String[] args) throws Exception {
    multipartSimple();
    multipartWithBuilder();
    binaryBodyBase64();
    System.out.println("MultiPartAndBinaryDemo — done.");
  }

  static void multipartSimple() throws Exception {
    Path temp = Files.createTempFile("ra-upload-", ".txt");
    Files.writeString(temp, "interview recap content");

    given()
        .baseUri(TestApis.HTTPBIN)
        .multiPart("file", temp.toFile())
        .formParam("description", "demo upload")
        .post("/post")
        .then()
        .statusCode(200)
        .body("files.file", notNullValue());

    Files.deleteIfExists(temp);
  }

  static void multipartWithBuilder() throws Exception {
    byte[] imageBytes = new byte[] {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, 0x00}; // minimal JPEG header bytes

    given()
        .baseUri(TestApis.HTTPBIN)
        .multiPart(
            new MultiPartSpecBuilder(imageBytes)
                .fileName("photo.jpg")
                .controlName("image")
                .mimeType("image/jpeg")
                .build())
        .post("/post")
        .then()
        .statusCode(200);
  }

  /**
   * Binary / octet-stream body — docs: set content type and pass bytes or File.
   * httpbin returns base64-encoded data in JSON field "data".
   */
  static void binaryBodyBase64() throws Exception {
    Path pdf = Files.createTempFile("sample-", ".bin");
    Files.write(pdf, new byte[] {0x25, 0x50, 0x44, 0x46}); // %PDF

    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType("application/octet-stream")
        .body(pdf.toFile())
        .post("/post")
        .then()
        .statusCode(200)
        .body("data", not(emptyString()));

    Files.deleteIfExists(pdf);
  }
}
