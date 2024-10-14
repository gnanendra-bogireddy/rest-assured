package authentication;

import io.restassured.RestAssured;

public class BasicAuthDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://httpbin.org";
        RestAssured.basePath = "/basic-auth";
        RestAssured.given().auth().basic("username", "password")
                .pathParam("user", "username").log().all()
                .get("/{user}/{passwd}", "password").then().log().all();
    }
}
