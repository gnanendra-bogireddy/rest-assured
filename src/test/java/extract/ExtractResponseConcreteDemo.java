package extract;

import common.TestApis;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * extract() — path, jsonPath(), headers, cookies, status, time, as(Class).
 */
public class ExtractResponseConcreteDemo {

  public static void main(String[] args) {
    ExtractableResponse<Response> ex =
        given()
            .baseUri(TestApis.JSON_PLACEHOLDER)
            .get("/posts/1")
            .then()
            .statusCode(200)
            .extract();

    System.out.println("path id=" + ex.path("id"));
    System.out.println("jsonPath title=" + ex.jsonPath().getString("title"));
    System.out.println("status=" + ex.statusCode());
    System.out.println("timeMs=" + ex.time());
    System.out.println("contentType=" + ex.contentType());
    System.out.println("header CT=" + ex.header("Content-Type"));

    String body = ex.response().asString();
    System.out.println("body length=" + body.length());

    Response raw =
        given().baseUri(TestApis.RESTFUL_API_DEV).accept(ContentType.JSON).get("/objects/5");
    var model = raw.as(model.ResponseModel.class);
    System.out.println("POJO name=" + model.name);

    System.out.println("ExtractResponseConcreteDemo — done.");
  }
}
