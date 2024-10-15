package builders;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderDemo {
    public static void main(String[] args) {

        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectHeader("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
        builder.expectHeader("Content-Type", "application/json");

        ResponseSpecification responseSpec = builder.build();

        // Now you can re-use the "responseSpec" in many different tests:

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2/pet/findByStatus";

        RestAssured.given().log().all()
                .auth().none()
                .header("api_key", "special_key")
                .queryParam("status", "available")
                .get().then().spec(responseSpec).log().all();

        RestAssured.reset();
        RestAssured.baseURI = "https://petstore.swagger.io";

        RestAssured.given().log().all()
                .auth().none()
                .header("api_key", "special_key")
                .pathParam("petId", "0")
                .delete("v2/pet/{petId}").then().spec(responseSpec).log().all();
    }
}
