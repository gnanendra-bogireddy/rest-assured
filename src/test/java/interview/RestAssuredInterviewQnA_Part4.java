package interview;

/**
 * REST Assured SME Interview Q&A - Part 4 (Questions 91-120)
 * Topic: Advanced Config, Filters, and Specifications
 */
public class RestAssuredInterviewQnA_Part4 {

    /*
     * 91. What is RequestSpecification?
     * Ans: An interface that allows you to group common request configurations (headers, base URI, auth, etc.).
     * 
     * 92. What is ResponseSpecification?
     * Ans: An interface that allows you to group common response validations (status code, content type, response time).
     * 
     * 93. How do you build a RequestSpecification?
     * Ans: Using RequestSpecBuilder().build().
     * 
     * 94. What is the benefit of using Specifications?
     * Ans: Code reusability, maintainability, and improved thread safety for parallel execution.
     * 
     * 95. What is a Filter in REST Assured?
     * Ans: A mechanism to intercept, log, or modify a request before it is sent or a response before it is returned.
     * 
     * 96. Name some built-in filters in REST Assured.
     * Ans: RequestLoggingFilter, ResponseLoggingFilter, ErrorLoggingFilter.
     * 
     * 97. How do you create a custom filter?
     * Ans: By implementing the Filter interface and overriding the filter method.
     * 
     * 98. How do you measure the response time for all requests using a filter?
     * Ans: By recording start time before ctx.next(req, res) and end time after.
     * 
     * 99. What is RestAssuredConfig?
     * Ans: A class used to configure advanced settings like timeouts, SSL, redirects, and encoders.
     * 
     * 100. How do you black-list specific headers from logs?
     * Ans: Using LogConfig.logConfig().blacklistHeader("headerName").
     * 
     * 101. What is the difference between log().all() and log().everything()?
     * Ans: Both are similar, but everything() is often used for logging both request and response.
     * 
     * 102. How do you handle redirects?
     * Ans: By configuring RedirectConfig to follow or not follow redirects.
     * 
     * 103. What is SSLConfig used for?
     * Ans: To configure keystores, truststores, and hostname verification for secure connections.
     * 
     * 104. How do you validate a JSON response against a JSON Schema?
     * Ans: then().body(JsonSchemaValidator.matchesJsonSchema(schemaFile)).
     * 
     * 105. What library is needed for JSON Schema validation?
     * Ans: io.rest-assured:json-schema-validator.
     * 
     * 106. What is the difference between soft assertions and hard assertions in REST Assured?
     * Ans: Hard assertions fail immediately; REST Assured's then().body() calls act as soft assertions if combined in one block.
     * 
     * 107. How do you handle sessions in REST Assured?
     * Ans: By using SessionFilter or manually passing sessionId.
     * 
     * 108. What is the use of the @BeforeClass annotation in TestNG for REST Assured?
     * Ans: To setup global baseURI and common specifications before tests run.
     * 
     * 109. How do you implement a Global Request Specification?
     * Ans: RestAssured.requestSpecification = mySpec;
     * 
     * 110. Is RestAssured.requestSpecification thread-safe?
     * Ans: No, static fields are shared across threads. For parallel execution, pass specs using .spec() to given().
     * 
     * 111. How do you disable URL encoding for a specific parameter?
     * Ans: given().urlEncodingEnabled(false).queryParam("p", "v")...
     * 
     * 112. What is the purpose of the DecoderConfig?
     * Ans: To specify how response content should be decoded (e.g., GZIP).
     * 
     * 113. How do you handle multiple filters?
     * Ans: By calling .filters(filter1, filter2) or adding them sequentially.
     * 
     * 114. How do you log only the request method and URI?
     * Ans: given().log().method().log().uri()...
     * 
     * 115. Can you use REST Assured with Cucumber?
     * Ans: Yes, it is very common to use REST Assured DSL inside Cucumber Step Definitions.
     * 
     * 116. How do you extract a specific cookie value?
     * Ans: response.getCookie("name").
     * 
     * 117. How do you extract all headers?
     * Ans: Headers headers = response.getHeaders();
     * 
     * 118. What is the use of the ProxySpecification?
     * Ans: To route requests through a proxy server (host, port, auth).
     * 
     * 119. How do you handle multipart/form-data without a file?
     * Ans: By using .multiPart("name", "value") for each field.
     * 
     * 120. How do you validate that the response time is less than 5 seconds?
     * Ans: then().time(lessThan(5L), TimeUnit.SECONDS).
     */
}
