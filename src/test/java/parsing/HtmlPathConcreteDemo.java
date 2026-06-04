package parsing;

import common.TestApis;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * HtmlPath — CSS-like selectors on HTML (docs: {@code response.htmlPath().getString("html.body.h1")}).
 */
public class HtmlPathConcreteDemo {

  public static void main(String[] args) {
    Response response = given().baseUri(TestApis.HTTPBIN).get("/html");

    String h1 = response.htmlPath().getString("html.body.h1");
    System.out.println("H1 text: " + h1);

    String h1Again = given().get(TestApis.HTTPBIN + "/html").htmlPath().get("html.body.h1");
    System.out.println("H1 again: " + h1Again);

    System.out.println("HtmlPathConcreteDemo — done.");
  }
}
