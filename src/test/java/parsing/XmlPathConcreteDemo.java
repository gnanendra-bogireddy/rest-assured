package parsing;

import common.TestApis;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * XmlPath — parse XML responses (docs: "Parsing XML").
 * Attribute access uses {@code @} prefix, e.g. {@code slideshow.@title}.
 */
public class XmlPathConcreteDemo {

  public static void main(String[] args) {
    Response response = given().baseUri(TestApis.HTTPBIN).get("/xml");

    XmlPath xml = response.xmlPath();
    // or: XmlPath xml = new XmlPath(response.asString());

    String title = xml.getString("slideshow.@title");
    String slideTitle = xml.getString("slideshow.slide[0].title");
    int slideCount = xml.getInt("slideshow.slide.size()");

    System.out.println("slideshow title=" + title);
    System.out.println("first slide=" + slideTitle + " count=" + slideCount);

    // inline then() with xml path
    given()
        .baseUri(TestApis.HTTPBIN)
        .get("/xml")
        .then()
        .body("slideshow.@title", equalTo("Sample Slide Show"))
        .body("slideshow.slide.title", org.hamcrest.Matchers.hasItem("Wake up to WonderWidgets!"));

    System.out.println("XmlPathConcreteDemo — done.");
  }
}
