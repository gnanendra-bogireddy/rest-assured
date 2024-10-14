package authentication;

import io.restassured.RestAssured;

public class ApiKeyDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "/store/inventory";
        RestAssured.given().log().all().auth().none()
                .header("api_key", "special-key")
                .get().then().log().all();
    }
}
