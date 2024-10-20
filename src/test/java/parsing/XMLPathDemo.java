package parsing;

import io.restassured.RestAssured;

public class XMLPathDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "http://httpbin.org";
        RestAssured.basePath = "/xml";

        String xpathValue = RestAssured.given().log().all()
                .auth().none().get()
                .xmlPath().get("/slideshow/slide").toString();

        System.out.println("Printing XMLPath value in console : " +xpathValue);
    }
}
