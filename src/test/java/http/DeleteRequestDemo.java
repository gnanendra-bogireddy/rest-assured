package http;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class DeleteRequestDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.given().log().all()
                .auth().none()
                .request(Method.DELETE, "/posts/1").then().log().all();

        RestAssured.given().log().all()
                .auth().none()
                .pathParam("id", "1")
                .delete("/posts/{id}").then().log().all();
    }
}
