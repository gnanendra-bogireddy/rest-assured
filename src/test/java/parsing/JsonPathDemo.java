package parsing;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import parsing.model.ResponseModel;

import java.util.Iterator;
import java.util.List;

public class JsonPathDemo {
    // The usage of "$" node is optional in latest versions of Rest assured.
    // JsonPath is bundled with Rest Assured. It is recommended to use after storing Response in a object.
    public static void main(String[] args) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        Response response = RestAssured.given().log().all()
                .auth().none()
                .pathParam("id", "1")
                .request(Method.GET, "/v2/pet/{id}")
                .then().log().all().extract().response();

        // We can get String values using json path by using get("expression') or getString("expression")
        System.out.println("String for the response is : " + response.jsonPath().get("category.name").toString());

        // We can get Array values using json path by using getList("expression)
        System.out.println("Array values from response is : " +response.jsonPath().getList("tags"));

        // We can get Array values using json path by using getInt("expression")
        System.out.println("Integer values from response is : " +response.jsonPath().getInt("category.id"));

        // Storing all the JsonPath and model class.
        RestAssured.reset();
        RestAssured.baseURI = "https://api.restful-api.dev";

        Response jsonPathResponse = RestAssured.given().log().all()
                .get("/objects").then()
                .log().all().extract().response();

        List<ResponseModel> list = jsonPathResponse.jsonPath().getList("$", ResponseModel.class);
        System.out.println("Printing Third element in Response " + list.get(2).getName());
        System.out.println("Printing Third element Color " + list.get(2).getData().getColor());
        System.out.println("Printing Third element Capacity " + list.get(2).getData().getCapacity());
    }
}
