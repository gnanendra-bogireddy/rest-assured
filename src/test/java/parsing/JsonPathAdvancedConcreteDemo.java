package parsing;

import common.TestApis;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Advanced JsonPath — find, findAll, collect, min/max (Groovy).
 */
public class JsonPathAdvancedConcreteDemo {

  public static void main(String[] args) {
    JsonPath jp = given().baseUri(TestApis.JSON_PLACEHOLDER).get("/users").jsonPath();

    String name = jp.getString("find { it.id == 5 }.name");
    List<String> inCity = jp.getList("findAll { it.address.city == 'Gwenborough' }.name");
    List<String> emails = jp.getList("collect { it.email }");

    System.out.println("user5=" + name);
    System.out.println("Gwenborough count=" + inCity.size());
    System.out.println("first email=" + emails.get(0));

    System.out.println("JsonPathAdvancedConcreteDemo — done.");
  }
}
