package serialization;

import common.TestApis;
import io.restassured.http.ContentType;
import model.DataModel;
import model.ResponseModel;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Serialization — Java → JSON request body (REST Assured docs: uses Jackson when on classpath).
 * Supports: String, Map, List, File, POJO, InputStream.
 */
public class SerializationConcreteDemo {

  public static void main(String[] args) {
    stringJsonBody();
    mapBody();
    pojoBody();
    System.out.println("SerializationConcreteDemo — done.");
  }

  static void stringJsonBody() {
    String json =
        """
        {"title":"foo","body":"bar","userId":1}
        """;
    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .contentType(ContentType.JSON)
        .body(json)
        .post("/posts")
        .then()
        .statusCode(201);
  }

  static void mapBody() {
    Map<String, Object> post = new HashMap<>();
    post.put("title", "RA Map");
    post.put("body", "content");
    post.put("userId", 1);

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .contentType(ContentType.JSON)
        .body(post)
        .post("/posts")
        .then()
        .statusCode(201)
        .body("title", equalTo("RA Map"));
  }

  /** POJO → JSON (Jackson annotations on model.*) */
  static void pojoBody() {
    ResponseModel payload = new ResponseModel();
    payload.name = "Google Pixel 6 Pro";
    DataModel data = new DataModel();
    data.year = 2023;
    data.price = 1099.99;
    payload.data = data;

    given()
        .baseUri(TestApis.RESTFUL_API_DEV)
        .contentType(ContentType.JSON)
        .body(payload)
        .post("/objects")
        .then()
        .statusCode(200)
        .body("name", equalTo("Google Pixel 6 Pro"));
  }
}
