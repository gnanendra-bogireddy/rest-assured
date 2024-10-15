package http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequestDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2/pet";

        String body = "{\n" +
                "  \"id\": 100,\n" +
                "  \"category\": {\n" +
                "    \"id\": 100,\n" +
                "    \"name\": \"Dog\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://doggie.com\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 100,\n" +
                "      \"name\": \"cutie\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RestAssured.given().log().all()
                .auth().none()
                .body(body)
                .header("api_key", "special-key")
                .contentType(ContentType.JSON)
                .post().then().log().all();
    }
}
