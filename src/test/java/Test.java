import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Test {


    public static void main(String[] args) {


        RestAssured.baseURI = "https://dummyjson.com";
        RestAssured.basePath = "/users";
            RequestSpecification requestSpec = new RequestSpecBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            ResponseSpecification responseSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType("application/json")
                    .build();

    }
}
