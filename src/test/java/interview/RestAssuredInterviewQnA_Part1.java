package interview;

/**
 * REST Assured SME Interview Q&A - Part 1 (Questions 1-30)
 * Topic: Basics, DSL, and Configuration
 */
public class RestAssuredInterviewQnA_Part1 {

    /*
     * 1. What is REST Assured?
     * Ans: A Java library for testing and validating RESTful web services. It uses a BDD-style DSL.
     * 
     * 2. What are the three main blocks of a REST Assured test?
     * Ans: given() (pre-requisites), when() (action/method), then() (assertions).
     * 
     * 3. How do you set a base URI globally?
     * Ans: RestAssured.baseURI = "https://api.com";
     * 
     * 4. What is the default port used by REST Assured?
     * Ans: 8080. It can be changed using RestAssured.port = 443;
     * 
     * 5. How do you reset REST Assured to its default settings?
     * Ans: RestAssured.reset();
     * 
     * 6. What is the difference between queryParam and pathParam?
     * Ans: queryParam adds to URL string (?id=1), pathParam replaces placeholders in the path ({id}).
     * 
     * 7. How do you log everything in a request?
     * Ans: given().log().all()...
     * 
     * 8. How do you log only if a validation fails?
     * Ans: then().log().ifValidationFails()...
     * 
     * 9. What are static imports in REST Assured?
     * Ans: Importing methods like given() and equalTo() statically to use them without class names.
     * 
     * 10. How do you send a body in a POST request?
     * Ans: given().body(payloadObject).when().post()...
     * 
     * 11. What is the importance of given()?
     * Ans: It is the entry point for setting up headers, cookies, params, and auth.
     * 
     * 12. Can we use REST Assured with TestNG/JUnit?
     * Ans: Yes, it is a library, not a runner. It integrates seamlessly with both.
     * 
     * 13. How do you specify the request content type?
     * Ans: given().contentType(ContentType.JSON)...
     * 
     * 14. How do you validate a status code?
     * Ans: then().statusCode(200)...
     * 
     * 15. What is the purpose of RestAssured.basePath?
     * Ans: To define the root path of the API that is common to all endpoints.
     * 
     * 16. How do you handle cookies in a request?
     * Ans: given().cookie("name", "value")... or given().cookies(map)...
     * 
     * 17. How do you extract the response as a string?
     * Ans: .extract().response().asString();
     * 
     * 18. What is the difference between response.asString() and response.asPrettyString()?
     * Ans: Pretty string includes formatting (indents/newlines) for better readability.
     * 
     * 19. How do you add multiple headers to a request?
     * Ans: given().headers("h1", "v1", "h2", "v2")... or using a Map.
     * 
     * 20. What is the use of relaxedHTTPSValidation()?
     * Ans: To bypass SSL certificate validation for self-signed certificates.
     * 
     * 21. How do you validate a response header?
     * Ans: then().header("Content-Type", "application/json")...
     * 
     * 22. What is Hamcrest?
     * Ans: An assertion library used by REST Assured for fluent matching (equalTo, contains, etc.).
     * 
     * 23. How do you check if a response body is empty?
     * Ans: then().body(isEmptyOrNullString())...
     * 
     * 24. What is the use of rootPath?
     * Ans: To set a common starting point for all body assertions to avoid path repetition.
     * 
     * 25. How do you pass multiple query parameters?
     * Ans: By calling .queryParam() multiple times or using .queryParams(map).
     * 
     * 26. How do you validate the status line?
     * Ans: then().statusLine("HTTP/1.1 200 OK")...
     * 
     * 27. Can REST Assured be used for SOAP services?
     * Ans: Yes, by sending XML in the body and setting appropriate headers, but it's primarily for REST.
     * 
     * 28. How do you measure the response time?
     * Ans: response.getTime() or then().time(lessThan(2000L)).
     * 
     * 29. What is the default timeout for REST Assured?
     * Ans: It depends on the underlying HTTP client (Apache HttpClient), usually no timeout by default.
     * 
     * 30. How do you enable URL encoding?
     * Ans: RestAssured.urlEncodingEnabled = true; (enabled by default).
     */
}
