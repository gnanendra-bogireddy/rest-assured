package authentication;

import io.restassured.RestAssured;

public class BearerAuthDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://httpbin.org";
        RestAssured.basePath = "/bearer";
        RestAssured.given().auth().none()
                .header("Authorization", "Bearer Token")
                .log().all()
                .get("").then().log().all();
    }
}
