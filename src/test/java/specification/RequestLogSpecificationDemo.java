package specification;

import io.restassured.RestAssured;
import io.restassured.specification.RequestLogSpecification;

public class RequestLogSpecificationDemo {
    public static void main(String[] args) {

        RequestLogSpecification logSpecification = RestAssured.with().log();
        logSpecification.all();

    }
}
