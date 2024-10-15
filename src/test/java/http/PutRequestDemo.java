package http;

import io.restassured.RestAssured;

public class PutRequestDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in";

        String body = "{\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        RestAssured.given().log().all()
                .auth().none().body(body)
                .put("/api/users/{id}", "2").then().log().all();
    }
}
