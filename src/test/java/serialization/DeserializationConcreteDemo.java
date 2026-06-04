package serialization;

import common.TestApis;
import common.auth.LoginResponse;
import io.restassured.http.ContentType;
import model.ResponseModel;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Deserialization — JSON/XML → Java (docs: {@code .as(Class)}, {@code jsonPath().getObject}).
 */
public class DeserializationConcreteDemo {

  public static void main(String[] args) {
    wholeBodyAsPojo();
    jsonPathPartial();
    listOfPojos();
    loginResponsePojo();
    System.out.println("DeserializationConcreteDemo — done.");
  }

  static void wholeBodyAsPojo() {
    ResponseModel obj =
        given()
            .baseUri(TestApis.RESTFUL_API_DEV)
            .accept(ContentType.JSON)
            .get("/objects/5")
            .then()
            .statusCode(200)
            .extract()
            .as(ResponseModel.class);

    System.out.println("object name=" + obj.name);
  }

  static void jsonPathPartial() {
    Double price =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .get("/products/1")
            .jsonPath()
            .getDouble("price");

    System.out.println("product price=" + price);
  }

  static void listOfPojos() {
    List<ResponseModel> list =
        given()
            .baseUri(TestApis.RESTFUL_API_DEV)
            .get("/objects")
            .jsonPath()
            .getList("$", ResponseModel.class);

    System.out.println("objects count=" + list.size());
  }

  static void loginResponsePojo() {
    LoginResponse login =
        given()
            .baseUri(TestApis.DUMMY_JSON)
            .contentType(ContentType.JSON)
            .body("{\"username\":\"" + TestApis.DUMMYJSON_USER + "\",\"password\":\"" + TestApis.DUMMYJSON_PASS + "\"}")
            .post("/auth/login")
            .as(LoginResponse.class);

    System.out.println("accessToken length=" + login.accessToken.length());
  }
}
