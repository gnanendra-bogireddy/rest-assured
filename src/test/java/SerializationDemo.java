import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.DataModel;
import model.ResponseModel;

public class SerializationDemo {
    // REST Assured supports mapping Java objects to and from JSON and XML.
    // For JSON you need to have either Jackson, Jackson2, Gson or Johnzon in the classpath
    // and for XML you need Jakarta EE or JAXB.

//    In this example REST Assured will serialize the object to JSON since the request content-type
//    is set to "application/json". It will first try to use Jackson if found in classpath
//    and if not Gson will be used. If you change the content-type to "application/xml"
//    REST Assured will serialize to XML using JAXB. If no content-type is defined REST Assured will
//    try to serialize in the following order:

//    JSON using Jackson 2 (Faster Jackson (databind))
//    JSON using Jackson (databind)
//    JSON using Gson
//    JSON using Johnzon
//    JSON-B using Eclipse Yasson
//    XML using Jakarta EE
//    XML using JAXB
//    REST Assured also respects the charset of the content-type. E.g.
//
//    Message message = new Message();
//    message.setMessage("My messagee");
//    given().
//    contentType("application/json; charset=UTF-16").
//    body(message).
//    when().
//    post("/message");

//    Create JSON from a HashMap
//    You can also create a JSON document by supplying a Map to REST Assured.
//
//    Map<String, Object>  jsonAsMap = new HashMap<>();
//    jsonAsMap.put("firstName", "John");
//    jsonAsMap.put("lastName", "Doe");
//    given().
//    contentType(JSON).
//    body(jsonAsMap).
//    when().
//    post("/somewhere").
//    then().
//    statusCode(200);
//    This will provide a JSON payload as:
//
//    { "firstName" : "John", "lastName" : "Doe" }
    public static void main(String[] args) {

        ResponseModel responseModel = new ResponseModel();
        responseModel.name = "Apple MacBook Pro 16";
        DataModel dataModel = new DataModel();
        dataModel.year = 2019;
        dataModel.price = 1849.99;
        dataModel.CPUModel = "Intel Core i9";
        dataModel.size = "1 TB";
        responseModel.data = dataModel;

        RestAssured.baseURI = "https://api.restful-api.dev";
        RestAssured.basePath = "/objects";

        RestAssured.given().log().all()
                .auth().none().body(responseModel)
                .contentType(ContentType.JSON)
                .post().then().log().all();
    }
}
