import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;

public class FormURLEncodedDemo {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://postman-echo.com";
        RestAssured.config = RestAssured.config().encoderConfig(new EncoderConfig().defaultContentCharset("UTF-8"));

        RestAssured.given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .post("/post").then().log().all();

        RestAssured.reset();
        RestAssured.baseURI = "https://httpbin.org";

        RestAssured.given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("custname", "John Doe")
                .formParam("custtel", "0000000000")
                .formParam("custemail", "john.doe@email.com")
                .formParam("size", "medium")
                .formParam("topping", "cheese")
                .formParam("topping", "mushroom")
                .formParam("delivery", "19:00")
                .formParam("comments", "Dont ring the bell")
                .post("/post").then().log().all();
    }
}
