package http;

import io.restassured.RestAssured;

public class PatchRequestDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in";

        String body = "{\n" +
                "    \"name\": \"John Doe\",\n" +
                "}";
        RestAssured.given().log().all()
                .auth().none().body(body)
                .patch("/api/users/{id}", "2").then().log().all();

        RestAssured.given().log().all()
                .pathParam("id", "2")
                .auth().none().get("/api/users/{id}").then().log().all();
    }
}
