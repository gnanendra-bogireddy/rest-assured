package graphql;

import common.TestApis;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * GraphQL over HTTP — POST JSON body with {@code query} field (public Countries API).
 */
public class GraphQLRequestDemo {

  public static void main(String[] args) {
    String query = "{ country(code: \"US\") { name capital currency } }";

    given()
        .baseUri("https://countries.trevorblades.com")
        .contentType(ContentType.JSON)
        .body(java.util.Map.of("query", query))
        .post("/graphql")
        .then()
        .statusCode(200)
        .body("data.country.name", equalTo("United States"))
        .body("data.country.capital", equalTo("Washington D.C."));

    System.out.println("GraphQLRequestDemo — done.");
  }
}
