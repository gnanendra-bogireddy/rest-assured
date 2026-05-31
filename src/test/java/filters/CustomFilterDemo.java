package filters;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.annotations.Test;

/**
 * REST Assured SME Recap: Custom Filters
 * 
 * Key Interview Points:
 * 1. Filter Interface: Allows you to intercept and modify requests/responses.
 * 2. Use Cases: Custom logging, Performance monitoring (time measurement), 
 *    Auth token renewal, Security auditing.
 * 3. Execution Order: Filters are executed in the order they are added.
 */
public class CustomFilterDemo {

    @Test(description = "Demonstrating a custom execution time filter")
    public void testCustomFilter() {
        
        Filter performanceFilter = (req, res, ctx) -> {
            long start = System.currentTimeMillis();
            Response response = ctx.next(req, res); // Proceed to next filter/request
            long end = System.currentTimeMillis();
            System.out.println("Request [ " + req.getMethod() + " " + req.getURI() + " ] took " + (end - start) + "ms");
            return response;
        };

        RestAssured.given()
                .filter(performanceFilter)
                .baseUri("https://jsonplaceholder.typicode.com")
                .get("/posts/1")
                .then()
                .statusCode(200);
    }
}
