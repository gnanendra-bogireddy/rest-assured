package parsing;

import io.restassured.RestAssured;

public class HTMLPathDemo {
    public static void main(String[] args) {

        String value = RestAssured.given().log().all().auth().none()
                .get("http://httpbin.org/html")
                .htmlPath().get("html.body.h1").toString();

        System.out.println("Header from Response is : " +value);
    }
}
