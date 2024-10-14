package authentication;

import io.restassured.RestAssured;

public class DigestAuthDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://httpbin.org";
        RestAssured.basePath = "/digest-auth/qop";
        RestAssured.given().auth().digest("username", "password")
                .pathParam("user", "username").log().all()
                .get("/{user}/{passwd}", "password").then().log().all();

    }
}
