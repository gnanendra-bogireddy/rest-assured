import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MultiPartSpecDemo {
    public static void main(String[] args) {
        // This is a API from Rapid API playground for OCR API. "https://rapidapi.com/api4ai-api4ai-default/api/ocr43"
        // URI = "https://ocr43.p.rapidapi.com/v1/results"
        // Custom-Headers - "x-rapidapi-key"="6ff0f5da7amshdf1cf71fc400f2ep1e008ajsn6d5f22516f7f"
        // Custom-Headers - "x-rapidapi-ua"="RapidAPI-Playground"
        // Custom-Headers - "x-rapidapi-host"="ocr43.p.rapidapi.com"

        RestAssured.baseURI = "https://ocr43.p.rapidapi.com";
        RestAssured.basePath = "/v1/results";

        MultiPartSpecBuilder multiPartSpecBuilder = new MultiPartSpecBuilder(new File("src/test/resources/sample.jpg"));
        multiPartSpecBuilder.fileName("sample.jpg");
        multiPartSpecBuilder.mimeType("image/jpeg");
        multiPartSpecBuilder.controlName("image");
        multiPartSpecBuilder.build();

        Map<String, String> headers = new HashMap<>();
        headers.put("x-rapidapi-ua","RapidAPI-Playground");
        headers.put("x-rapidapi-key","6ff0f5da7amshdf1cf71fc400f2ep1e008ajsn6d5f22516f7f");
        headers.put("x-rapidapi-host","ocr43.p.rapidapi.com");
        RestAssured.given().log().all()
                .queryParam("algo","simple-words")
                .contentType(ContentType.MULTIPART)
                .headers(headers)
                .multiPart(multiPartSpecBuilder.build())
                .post().then().log().all();

    }
}
