package specification;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

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

        // We can define request spec builder by using the code below.

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setAuth(RestAssured.oauth2("accessToken", OAuthSignature.HEADER));
        builder.addParam("parameter1", "parameterValue");
        builder.addHeader("header1", "headerValue");
        builder.addCookie("name");
        RequestSpecification requestSpec = builder.build();

        RestAssured.given().
                spec(requestSpec).
                param("parameter2", "paramValue").
                when().
                get("/something").
                then().
                body("x.y.z", equalTo("something"));
    }
}
