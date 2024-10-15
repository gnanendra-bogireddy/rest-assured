package http;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetRequestDemo {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.given().log().all()
                .auth().none()
                .request(Method.GET, "/posts")
                .then().log().all();

        RestAssured.given().log().all()
                .auth().none()
                .get("/posts/1").then().log().all();

        RestAssured.given().log().all()
                .auth().none()
                .pathParam("id", "1")
                .get("/posts/{id}/comments")
                .then().log().all();

        RestAssured.given().log().all()
                .auth().none()
                .queryParam("postId", "1")
                .get("/comments").then().log().all();
    }
}
