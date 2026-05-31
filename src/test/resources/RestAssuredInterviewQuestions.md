# REST Assured Automation SME Recap

This document contains key concepts, common interview questions, and best practices for REST Assured.

## 1. Core Architecture
- **DSL (Domain Specific Language):** REST Assured uses a BDD-style DSL (`given()`, `when()`, `then()`).
- **Static Imports:** Always import `io.restassured.RestAssured.*` and `org.hamcrest.Matchers.*`.

## 2. Key Interview Questions & Answers

### Q: What is the difference between `given()`, `when()`, and `then()`?
- **Given:** Sets up preconditions (Headers, Body, Auth, Params, Cookies).
- **When:** Specifies the action/method (GET, POST, PUT, DELETE) and the resource path.
- **Then:** Validates the response (Status code, Body content, Headers, Time).

### Q: How do you achieve thread safety in REST Assured?
Avoid using the static `RestAssured` class for settings (like `RestAssured.baseURI`) when running in parallel. Instead, use **RequestSpecification** and **ResponseSpecification** objects and pass them to each request using `.spec()`.

### Q: How do you handle authentication?
- **Basic Auth:** `.auth().basic("user", "pass")`
- **Preemptive Basic Auth:** `.auth().preemptive().basic("user", "pass")`
- **OAuth 2.0:** `.auth().oauth2("token")`
- **Digest/OAuth1:** Supported via `.auth().digest()` and `.auth().oauth()`.

### Q: How do you extract values from a response?
- **Single value:** `response.jsonPath().getString("path.to.element")`
- **Full Object (Deserialization):** `response.as(MyClass.class)`
- **List of Objects:** `response.jsonPath().getList("path", MyClass.class)`

### Q: What are Groovy GPath expressions?
Powerful path notations like `find`, `findAll`, `collect`, `max`, `min` used to query JSON/XML.
*Example:* `findAll { it.price > 100 }.name`

### Q: How do you validate a JSON Schema?
Use the `json-schema-validator` module:
`.then().body(matchesJsonSchemaInClasspath("schema.json"))`

### Q: What is a Filter in REST Assured?
An interface used to intercept and modify requests/responses. Useful for custom logging, security, or performance tracking.

## 3. SME Best Practices
1. **Never hardcode URLs:** Use Global Config or Request Specs.
2. **Use POJOs for Payloads:** Avoid large Strings or Maps for complex JSON.
3. **Validate status codes first:** Fail fast before checking the body.
4. **Use Soft Assertions:** If one body check fails, you might still want to see the others (use `then().body(..., ...)`).
5. **Log only on failure:** Use `.log().ifValidationFails()` to keep CI logs clean.
