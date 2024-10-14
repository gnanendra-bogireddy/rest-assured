package specification;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResponseSpecificationDemo {
    // Instead of having to duplicate response expectations for
    // different tests you can re-use an entire specification. To do this you define
    // a specification using the ResponseSpecBuilder.
    // E.g. let's say you want to make sure that the expected status code is 200 and that the
    // size of the JSON array "x.y" has size 2 in several tests you can define
    // a ResponseSpecBuilder like this
    public static void main(String[] args) {

        RestAssured.baseURI = "https://httpbin.org";
        ResponseSpecification responseSpecification = RestAssured.expect()
                .statusCode(200).contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .rootPath("headers.Accept-Language")
                .log().all();

        RestAssured.given()
                .auth().none().get("/get")
                .then().spec(responseSpecification);

        RestAssured.given()
                .auth().none().post("/post")
                .then().spec(responseSpecification);

        RestAssured.given()
                .auth().none().patch("/patch")
                .then().spec(responseSpecification);

        // We can build ResponseSpecBuilder by using the below code also.

        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectBody("x.y.size()", is(2));
        ResponseSpecification responseSpec = builder.build();

        // Now you can re-use the "responseSpec" in many different tests:

        RestAssured.when().
                get("/something").
                then().
                spec(responseSpec).
                body("x.y.z", equalTo("something"));
    }
}
