package request;

import common.TestApis;
import io.restassured.http.ContentType;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Request body from File and classpath resource.
 */
public class FileClasspathBodyDemo {

  public static void main(String[] args) {
    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.JSON)
        .body(new File("src/test/resources/sample.json"))
        .post("/post")
        .then()
        .statusCode(200)
        .body("json.people[0].firstName", equalTo("Joe"));

    given()
        .baseUri(TestApis.HTTPBIN)
        .contentType(ContentType.JSON)
        .body(FileClasspathBodyDemo.class.getResourceAsStream("/sample.json"))
        .post("/post")
        .then()
        .statusCode(200);

    System.out.println("FileClasspathBodyDemo — done.");
  }
}
