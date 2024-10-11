package specification;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationDemo {
    // Instead of having to duplicate requests for
    // different tests you can re-use an entire specification. To do this you define
    // a specification using the RequestSpecBuilder.
    public static void main(String[] args) {

        // This properties will be applicable for all the test cases.
        RequestSpecification requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json")
                .auth().none()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/users")
                .pathParam("id", 1);

        RestAssured.given()
                .spec(requestSpecification)
                .get("/{id}/albums")
                .then().log().all();
        RestAssured.given()
                .spec(requestSpecification)
                .get("/{id}/todos")
                .then().log().all();
        RestAssured.given()
                .spec(requestSpecification)
                .get("/{id}/posts")
                .then().log().all();
    }
}
