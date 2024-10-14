package builders;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

// We can build MultiPartSpecBuilder by using file, control name and file instance.
// We can directly pass the file instance to the multiPart method.
// we can use input stream and byte[] array to pass the file data.
// we have so many overloaded methods in Rest assured to pass the multipart.
// mime type is type of data we have in file not Content-Type that we define for API.
// default control name is file. but we can have custom defined control name for file uploads.
// we can send java objects along with files in multipart method.In other multipart we can define other types of mime types also.
public class MultiPartSpecBuilderDemo {
    public static void main(String[] args) throws FileNotFoundException {
        // When sending larger amount of data to the server it's common to use the multipart form data
        // technique. Rest Assured provide methods called multiPart that allows you to specify a file,
        // byte-array, input stream or text to upload. In its simplest form you can upload a file like this:

//        given().
//                multiPart(new File("/path/to/file")).
//                when().
//                post("/upload");

        // It will assume a control name called "file". In HTML the control name is the attribute name of the input tag.
        // To clarify let's look at the following HTML form:

//        <form id="uploadForm" action="/upload" method="post" enctype="multipart/form-data">
//        <input type="file" name="file" size="40">
//        <input type=submit value="Upload!">
//        </form>

        // The control name in this case is the name of the input tag with name "file".
        // If you have a different control name then you need to specify it:

//        given().
//                multiPart("controlName", new File("/path/to/file")).
//                when().
//                post("/upload");

        // It's also possible to supply multiple "multi-parts" entities in the same request:

//        byte[] someData = ..
//        given().
//                multiPart("controlName1", new File("/path/to/file")).
//                multiPart("controlName2", "my_file_name.txt", someData).
//                multiPart("controlName3", someJavaObject, "application/json").
//                when().
//                post("/upload");

        // For more advanced use case use multipart spec builder

//        Greeting greeting = new Greeting();
//        greeting.setFirstName("John");
//        greeting.setLastName("Doe");
//
//        given().
//                multiPart(new MultiPartSpecBuilder(greeting, ObjectMapperType.JACKSON_2)
//                        .fileName("greeting.json")
//                        .controlName("text")
//                        .mimeType("application/vnd.custom+json").build()).
//                when().
//                post("/multipart/json").
//                then().
//                statusCode(200);

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "/pet/1/uploadImage";

        File file = new File("src/test/resources/sample.json");
        MultiPartSpecBuilder multiPartSpecBuilder = new MultiPartSpecBuilder(file);
        multiPartSpecBuilder.mimeType("application/json");
        multiPartSpecBuilder.controlName("file");
        multiPartSpecBuilder.fileName("sample.json");
        multiPartSpecBuilder.build();

        RestAssured.given().auth().none()
                .log().all()
                .header("api_key", "special-key")
                .header("Content-Type", "multipart/form-data")
                .multiPart(multiPartSpecBuilder.build())
                .multiPart("additionalMetadata", "formData1")
                .post().then().log().all();

        RestAssured.given().auth().none().log().all()
                .header("api_key", "special-key")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new  File("src/test/resources/sample.json"), "application/json")
                .multiPart("additionalMetadata", "formData2")
                .post().then().log().all();

        RestAssured.given().auth().none().log().all()
                .header("api_key", "special-key")
                .header("Content-Type", "multipart/form-data")
                .multiPart(new  File("src/test/resources/sample.json"))
                .multiPart("additionalMetadata", "formData3", "text/plain")
                .post().then().log().all();

        FileInputStream fileInputStream = new FileInputStream("src/test/resources/sample.json");
        RestAssured.given().auth().none().log().all()
                .header("api_key", "special-key")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", "sample.json", fileInputStream, "application/json")
                .multiPart("additionalMetadata", "formData4", "text/plain")
                .post().then().log().all();
    }
}
