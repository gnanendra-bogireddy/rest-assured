package model;

/**
 * REST Assured SME Interview Q&A - Part 5 (Questions 121-150)
 * Topic: REST Principles, Troubleshooting, and Framework Design
 */
public class RestAssuredInterviewQnA_Part5 {

    /*
     * 121. What is the difference between REST and SOAP?
     * Ans: REST is an architectural style (uses HTTP methods, lightweight); SOAP is a protocol (XML-based, strict standards like WSDL).
     * 
     * 122. What are the common HTTP methods and their uses?
     * Ans: GET (Retrieve), POST (Create), PUT (Replace), PATCH (Modify), DELETE (Remove).
     * 
     * 123. What is Idempotency in REST?
     * Ans: An operation that can be performed multiple times with the same result (GET, PUT, DELETE).
     * 
     * 124. What is the difference between PUT and PATCH?
     * Ans: PUT replaces the entire resource; PATCH applies a partial update.
     * 
     * 125. What is HATEOAS?
     * Ans: Hypermedia As The Engine Of Application State. It provides links in the response to navigate the API.
     * 
     * 126. How do you handle a "415 Unsupported Media Type" error?
     * Ans: By checking the Content-Type header in the request to ensure it matches what the server expects.
     * 
     * 127. How do you handle a "405 Method Not Allowed" error?
     * Ans: By verifying that the HTTP method (e.g., POST) is actually supported for that specific endpoint.
     * 
     * 128. What is the difference between a 401 and 403 status code?
     * Ans: 401 is Unauthenticated (who are you?); 403 is Unauthorized/Forbidden (I know who you are, but you can't do this).
     * 
     * 129. How do you test for API performance using REST Assured?
     * Ans: By asserting on response times and using filters to log latencies across many requests.
     * 
     * 130. What is a "Payload" in API testing?
     * Ans: The actual data sent in the body of a request or received in a response.
     * 
     * 131. How do you handle cyclic dependencies during POJO serialization?
     * Ans: Using Jackson annotations like @JsonManagedReference and @JsonBackReference.
     * 
     * 132. What is the benefit of a Data-Driven framework in REST Assured?
     * Ans: Testing multiple sets of data (e.g., valid/invalid inputs) using a single test script and TestNG DataProviders.
     * 
     * 133. How do you handle dynamic data in API tests?
     * Ans: By using random generators (like Java Faker) or extracting values from previous API calls.
     * 
     * 134. What is API Chaining?
     * Ans: Passing the output of one API (e.g., an ID or Token) as the input to a subsequent API.
     * 
     * 135. How do you implement API Chaining in REST Assured?
     * Ans: Extracting value using jsonPath() and passing it to the next given()...
     * 
     * 136. What is the use of the ParserConfig?
     * Ans: To specify default parsers for content types that are not automatically recognized.
     * 
     * 137. How do you debug a failing REST Assured test?
     * Ans: By using .log().all() and carefully checking the response status, headers, and body against requirements.
     * 
     * 138. Can we use REST Assured for UI testing?
     * Ans: No, it is strictly for HTTP-based API testing.
     * 
     * 139. How do you validate an empty JSON array?
     * Ans: then().body("list", hasSize(0)) or .body("list", empty()).
     * 
     * 140. What is the difference between a URI and a URL?
     * Ans: URL is a specific type of URI that provides the location of the resource.
     * 
     * 141. How do you handle "Connection Refused" errors?
     * Ans: Verify the server is running, the port is correct, and there are no firewall issues.
     * 
     * 142. What is a "Mock" in API testing?
     * Ans: A simulated API that returns predefined responses, useful when the real API is under development.
     * 
     * 143. Name some tools for Mocking APIs.
     * Ans: WireMock, Mockito (for unit tests), Postman Mock Servers.
     * 
     * 144. How do you extract a value from a response without using then()?
     * Ans: Response response = given().get(); String val = response.path("path.to.val");
     * 
     * 145. What is the use of the @JsonInclude(JsonInclude.Include.NON_NULL) annotation?
     * Ans: To prevent null fields in a POJO from being included in the serialized JSON payload.
     * 
     * 146. How do you set a custom header for all requests in a test class?
     * Ans: Using a RequestSpecification defined in a @BeforeClass method.
     * 
     * 147. What is the role of an API Gateway?
     * Ans: It acts as a single entry point for microservices, handling auth, rate limiting, and routing.
     * 
     * 148. How do you validate the encoding of the response?
     * Ans: then().header("Content-Encoding", "gzip").
     * 
     * 149. What is "Statelessness" in REST?
     * Ans: The server does not store any client state between requests; every request must be self-contained.
     * 
     * 150. What is the most important quality of a good API test?
     * Ans: Independence. A test should be able to run and pass regardless of the state of other tests.
     */
}
