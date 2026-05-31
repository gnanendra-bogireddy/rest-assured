package model;

/**
 * REST Assured SME Interview Q&A - Part 3 (Questions 61-90)
 * Topic: Authentication, Serialization, and Multi-part
 */
public class RestAssuredInterviewQnA_Part3 {

    /*
     * 61. What are the different types of authentication supported by REST Assured?
     * Ans: Basic, Digest, Form, OAuth 1.0, OAuth 2.0.
     * 
     * 62. How do you implement Basic Auth?
     * Ans: given().auth().basic("user", "pass")...
     * 
     * 63. What is Preemptive Authentication?
     * Ans: Sending the auth header before the server sends a 401 challenge.
     * 
     * 64. How do you implement Preemptive Basic Auth?
     * Ans: given().auth().preemptive().basic("user", "pass")...
     * 
     * 65. What is the difference between challenged and preemptive auth?
     * Ans: Challenged waits for server to ask for creds; Preemptive sends them immediately.
     * 
     * 66. How do you implement OAuth 2.0?
     * Ans: given().auth().oauth2("access_token")...
     * 
     * 67. How do you handle API Key authentication?
     * Ans: Usually by passing it as a header or query parameter: given().header("apikey", "value")...
     * 
     * 68. What is Serialization in REST Assured?
     * Ans: Converting a Java Object (POJO/Map) into JSON or XML format for the request body.
     * 
     * 69. What is Deserialization?
     * Ans: Converting a response body (JSON/XML) back into a Java Object (POJO).
     * 
     * 70. Which libraries are needed for JSON serialization?
     * Ans: Jackson or Gson must be in the classpath.
     * 
     * 71. How do you deserialize a response into a POJO?
     * Ans: MyClass obj = response.as(MyClass.class);
     * 
     * 72. What is the purpose of the @JsonIgnoreProperties(ignoreUnknown = true) annotation?
     * Ans: In Jackson, it tells the parser to ignore fields in JSON that don't exist in the POJO.
     * 
     * 73. How do you perform a partial deserialization?
     * Ans: By using jsonPath().getObject("path", MyClass.class).
     * 
     * 74. Can you use a Map for serialization?
     * Ans: Yes, REST Assured automatically converts a Map to JSON if a JSON content-type is set.
     * 
     * 75. How do you upload a file in REST Assured?
     * Ans: Using .multiPart("file", new File("path"))...
     * 
     * 76. What is MultiPartSpecBuilder?
     * Ans: A builder used to create complex multi-part requests with custom control names and mime types.
     * 
     * 77. How do you set the MIME type for a file upload?
     * Ans: .multiPart("file", file, "image/jpeg").
     * 
     * 78. What is the difference between formParam and multiPart?
     * Ans: formParam is for standard form-urlencoded data; multiPart is for binary data/files.
     * 
     * 79. How do you handle Digest Authentication?
     * Ans: given().auth().digest("user", "pass")...
     * 
     * 80. How do you handle OAuth 1.0?
     * Ans: given().auth().oauth("consumerKey", "consumerSecret", "accessToken", "secret")...
     * 
     * 81. Does REST Assured support automatic content-type detection for serialization?
     * Ans: Yes, it tries to detect based on the object type or global settings.
     * 
     * 82. How do you deserialize an array of objects?
     * Ans: MyClass[] array = response.as(MyClass[].class);
     * 
     * 83. What is the role of the ObjectMapper in REST Assured?
     * Ans: It is the internal engine (Jackson/Gson) that handles the conversion between Java and JSON.
     * 
     * 84. How do you specify a custom ObjectMapper?
     * Ans: By passing it to the as() method or configuring it in RestAssuredConfig.
     * 
     * 85. Can you send a List as a body?
     * Ans: Yes, it will be serialized as a JSON array.
     * 
     * 86. How do you handle Form Authentication (Login page)?
     * Ans: given().auth().form("user", "pass")...
     * 
     * 87. What is the benefit of using POJOs over Maps?
     * Ans: Type safety, better readability, and easier maintenance of complex structures.
     * 
     * 88. How do you validate an XML response against an XSD schema?
     * Ans: then().body(matchesXsdInClasspath("schema.xsd")).
     * 
     * 89. How do you handle Bearer Token in a header manually?
     * Ans: given().header("Authorization", "Bearer " + token)...
     * 
     * 90. What is the difference between Jackson and Gson?
     * Ans: Both are JSON processors; Jackson is generally faster and more feature-rich (default in Spring).
     */
}
