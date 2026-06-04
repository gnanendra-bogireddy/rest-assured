package parsing;

import common.TestApis;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.ResponseModel;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * JsonPath — REST Assured bundled Jayway/Groovy paths (docs: "Parsing JSON").
 */
public class JsonPathConcreteDemo {

  public static void main(String[] args) {
    Response response = given().baseUri(TestApis.JSON_PLACEHOLDER).get("/users");

    JsonPath jp = response.jsonPath();

    // scalars
    String firstName = jp.getString("[0].name");
    int id = jp.getInt("[0].id");
    String city = jp.getString("[0].address.city");

    // lists
    List<String> names = jp.getList("name");
    List<Integer> ids = jp.getList("id");

    // Groovy
    String user5 = jp.getString("find { it.id == 5 }.name");
    List<String> cities = jp.getList("findAll { it.address.city == 'Gwenborough' }.name");

    System.out.println("first=" + firstName + " id=" + id + " city=" + city);
    System.out.println("names count=" + names.size() + " user5=" + user5);
    System.out.println("Gwenborough users=" + cities);

    // POJO list from array root
    List<ResponseModel> products =
        given().baseUri(TestApis.RESTFUL_API_DEV).get("/objects").jsonPath().getList("$", ResponseModel.class);
    System.out.println("restful objects: " + products.get(0).name);

    // extract().path() shorthand
    String title = given().baseUri(TestApis.JSON_PLACEHOLDER).get("/posts/1").then().extract().path("title");
    System.out.println("post title=" + title);

    System.out.println("JsonPathConcreteDemo — done.");
  }
}
