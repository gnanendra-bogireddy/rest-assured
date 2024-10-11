package specification;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

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
    }
}
