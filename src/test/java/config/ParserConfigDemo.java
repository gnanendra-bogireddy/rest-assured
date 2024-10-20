package config;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class ParserConfigDemo {
    // REST Assured providers predefined parsers for e.g. HTML, XML and JSON.
    // But you can parse other kinds of content by registering a predefined parser for unsupported content-types using
    // RestAssured.registerParser(<content-type>, <parser>);
    public static void main(String[] args) {

        // Registering XML Parser
        RestAssured.registerParser("application/vnd.uoml+xml", Parser.XML);

        //You can also unregister a parser using
        RestAssured.unregisterParser("application/vnd.uoml+xml");

        //Parsers can also be specified per "request":
        RestAssured.given().get().then().using().parser("application/vnd.uoml+xml", Parser.XML);

    }
}
