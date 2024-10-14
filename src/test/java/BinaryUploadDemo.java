import io.restassured.RestAssured;

import java.io.File;

// Rest assured supports binary uploads.It encodes data in Base64 by default.
// It does not support binary types (0 and 1)
public class BinaryUploadDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://host:port";
        RestAssured.basePath = "/path";

        RestAssured.given().log().all()
                .contentType("application/pdf")
                .body(new File("file path"))
                .post().then().log().all();
    }
}
