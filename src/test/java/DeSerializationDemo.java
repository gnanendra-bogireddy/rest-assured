import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.DataModel;
import model.ResponseModel;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Deserialization — JSON/XML → Java objects.
 * <p>
 * Requirements: Jackson (JSON) or JAXB (XML) on classpath; response Content-Type must match
 * (e.g. {@code application/json} for Jackson).
 * <p>
 * Methods:
 * <ul>
 *   <li>{@code response.as(MyClass.class)} — whole body</li>
 *   <li>{@code response.jsonPath().getObject("path", MyClass.class)} — nested fragment</li>
 *   <li>{@code response.jsonPath().getList("$", MyClass.class)} — JSON array → List</li>
 * </ul>
 * Use {@code @JsonIgnoreProperties(ignoreUnknown = true)} and {@code @JsonAlias} for evolving APIs.
 */
public class DeSerializationDemo {

  public static void main(String[] args) {
    demonstrateSingleObject();
    demonstrateListMapping();
    demonstratePartialPath();
  }

  private static void demonstrateSingleObject() {
    System.out.println("--- as(Class) ---");
    ResponseModel object =
        given()
            .baseUri("https://api.restful-api.dev")
            .accept(ContentType.JSON)
            .get("/objects/5")
            .then()
            .statusCode(200)
            .extract()
            .as(ResponseModel.class);

    System.out.println("id=" + object.id + " name=" + object.name);
    if (object.data != null) {
      System.out.println("year=" + object.data.year + " price=" + object.data.price);
    }
  }

  private static void demonstrateListMapping() {
    System.out.println("--- getList(\"$\", Class) ---");
    List<ResponseModel> list =
        given()
            .baseUri("https://api.restful-api.dev")
            .get("/objects")
            .jsonPath()
            .getList("$", ResponseModel.class);

    System.out.println("objects count: " + list.size());
    System.out.println("first name: " + list.get(0).name);
  }

  private static void demonstratePartialPath() {
    System.out.println("--- getObject(path, Class) ---");
    DataModel data =
        given()
            .baseUri("https://api.restful-api.dev")
            .get("/objects/5")
            .jsonPath()
            .getObject("data", DataModel.class);

    System.out.println("partial data.year=" + data.year);
  }
}
