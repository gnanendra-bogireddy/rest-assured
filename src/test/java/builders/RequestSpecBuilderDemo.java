package builders;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RequestSpecBuilderDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";

        String body = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"John\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"email\": \"john.doe@email.com\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"phone\": \"0000000000\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "]";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("api-key", "special-key");

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBody(body);
        builder.log(LogDetail.ALL);
        builder.addHeaders(headers);
        builder.addFilter(new ErrorLoggingFilter());
        RequestSpecification requestSpec = builder.build();

        RestAssured.given()
                .spec(requestSpec)
                .when().post("/user/createWithList")
                .then().log().all();

        RestAssured.given()
                .spec(requestSpec)
                .when().post("/user/createWithArray")
                .then().log().all();
    }
}
