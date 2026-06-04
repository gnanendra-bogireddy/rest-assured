package params;

import common.TestApis;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * param() / params() — generic parameters; query for GET, form for POST (docs).
 */
public class RequestParametersDemo {

  public static void main(String[] args) {
    Map<String, String> query = new HashMap<>();
    query.put("_limit", "3");
    query.put("_start", "0");

    given()
        .baseUri(TestApis.JSON_PLACEHOLDER)
        .params(query)
        .get("/posts")
        .then()
        .body("size()", equalTo(3));

    given()
        .baseUri(TestApis.HTTPBIN)
        .param("key", "value")
        .get("/get")
        .then()
        .body("args.key", equalTo("value"));

    Map<String, String> form = Map.of("field1", "a", "field2", "b");
    given()
        .baseUri(TestApis.HTTPBIN)
        .params(form)
        .post("/post")
        .then()
        .body("form.field1", equalTo("a"));

    System.out.println("RequestParametersDemo — done.");
  }
}
