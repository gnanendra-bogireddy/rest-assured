package parsing;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * REST Assured SME Recap: XML Path Parsing
 * 
 * Key Interview Points:
 * 1. XmlPath: Used to query XML responses using a path notation.
 * 2. GPath for XML: Supports similar filtering as JsonPath (e.g., .find { it.@id == '1' }).
 * 3. Attributes: Accessed using '@' symbol (e.g., slideshow.@title).
 */
public class XMLPathDemo {

    @Test(description = "Parsing XML response using XmlPath")
    public void testXmlParsing() {
        RestAssured.baseURI = "http://httpbin.org";
        
        String response = RestAssured.given()
                .get("/xml")
                .asString();

        XmlPath xmlPath = new XmlPath(response);
        
        // Example GPath for XML
        String title = xmlPath.getString("slideshow.@title");
        System.out.println("Slideshow Title: " + title);

        // Get value of first slide's title
        String firstSlideTitle = xmlPath.getString("slideshow.slide[0].title");
        System.out.println("First Slide Title: " + firstSlideTitle);
    }
}
