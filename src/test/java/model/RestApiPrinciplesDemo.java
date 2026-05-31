package model;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

/**
 * REST API Principles & HTTP Status Codes Recap
 * 
 * This class serves as a theoretical and practical guide for Automation SMEs.
 * It documents the foundational principles of REST and the most common status codes.
 */
public class RestApiPrinciplesDemo {

    /**
     * 1. REST ARCHITECTURAL PRINCIPLES (Constraints)
     * 
     * - CLIENT-SERVER: Separation of concerns. The UI is independent of data storage.
     * - STATELESS: Each request from client to server must contain all information to understand the request. 
     *              The server does not store session context.
     * - CACHEABLE: Responses must define themselves as cacheable or not to improve performance.
     * - UNIFORM INTERFACE: Simplifies the architecture. Resource identification (URI), 
     *                     Resource manipulation through representations (JSON/XML), 
     *                     Self-descriptive messages, and HATEOAS.
     * - LAYERED SYSTEM: A client cannot tell whether it is connected directly to the end server or an intermediary.
     * - CODE ON DEMAND (Optional): Servers can temporarily extend client functionality by transferring executable code.
     */
    @Test(description = "Recap of REST Principles")
    public void restPrinciplesRecap() {
        System.out.println("REST = REpresentational State Transfer");
        System.out.println("Main Constraints: Statelessness, Client-Server, Uniform Interface, Cacheable, Layered System.");
    }

    /**
     * 2. HTTP STATUS CODES CATEGORIES
     * 
     * 1xx (Informational): Request received, continuing process.
     * 2xx (Success): The action was successfully received, understood, and accepted.
     * 3xx (Redirection): Further action must be taken to complete the request.
     * 4xx (Client Error): The request contains bad syntax or cannot be fulfilled.
     * 5xx (Server Error): The server failed to fulfill an apparently valid request.
     */
    
    @Test(description = "Common Status Codes & When they occur")
    public void commonStatusCodes() {
        // --- 2xx SUCCESS ---
        // 200 OK: Standard response for successful HTTP requests.
        // 201 Created: Request fulfilled, resulted in a new resource being created (POST).
        // 204 No Content: Request processed successfully, but no content is returned (DELETE/PUT).

        // --- 3xx REDIRECTION ---
        // 301 Moved Permanently: The URL of the requested resource has been changed permanently.
        // 304 Not Modified: Resource has not been modified since the last request (Caching).

        // --- 4xx CLIENT ERRORS ---
        // 400 Bad Request: Server cannot process request due to client error (e.g., malformed JSON).
        // 401 Unauthorized: Lacks valid authentication credentials.
        // 403 Forbidden: Server understood the request but refuses to authorize it (e.g., no permissions).
        // 404 Not Found: Resource not found.
        // 405 Method Not Allowed: HTTP method not supported for this resource (e.g., POST on a read-only endpoint).
        // 415 Unsupported Media Type: Payload format is not supported (e.g., sending XML when JSON is expected).

        // --- 5xx SERVER ERRORS ---
        // 500 Internal Server Error: Generic error message when an unexpected condition was encountered.
        // 502 Bad Gateway: Server (acting as proxy) received an invalid response from upstream server.
        // 503 Service Unavailable: Server is currently unable to handle the request (Overload/Maintenance).
        // 504 Gateway Timeout: Server (acting as proxy) did not receive a timely response from upstream server.
        
        System.out.println("Interview Tip: Always distinguish between 401 (Unauthenticated) and 403 (Unauthorized).");
    }

    /**
     * 3. IDEMPOTENCY IN REST
     * 
     * An operation is IDEMPOTENT if multiple identical requests have the same effect as a single request.
     * - GET: Idempotent (Read-only)
     * - PUT: Idempotent (Update - replaces the whole resource)
     * - DELETE: Idempotent (Deleting same resource twice has same effect)
     * - POST: NOT Idempotent (Multiple POSTs usually create multiple resources)
     * - PATCH: Usually NOT Idempotent (Partial updates)
     */
    @Test(description = "Idempotency Recap")
    public void idempotencyRecap() {
        System.out.println("Safe Methods: GET, HEAD (They don't change resource state).");
        System.out.println("Idempotent Methods: GET, PUT, DELETE, OPTIONS, HEAD.");
    }
}
