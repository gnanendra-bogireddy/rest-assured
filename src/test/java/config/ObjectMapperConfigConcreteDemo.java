package config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.TestApis;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

/**
 * ObjectMapperConfig — Jackson/Gson selection and custom ObjectMapper factory.
 */
public class ObjectMapperConfigConcreteDemo {

  public static void main(String[] args) {
    RestAssured.reset();

    RestAssured.config =
        RestAssured.config()
            .objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                    .defaultObjectMapperType(ObjectMapperType.JACKSON_2)
                    .jackson2ObjectMapperFactory(
                        (type, charset) -> {
                          ObjectMapper om = new ObjectMapper();
                          om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                          return om;
                        }));

    var model =
        given()
            .baseUri(TestApis.RESTFUL_API_DEV)
            .get("/objects/5")
            .as(model.ResponseModel.class);

    System.out.println("deserialized with custom mapper: " + model.name);

    RestAssured.reset();
    System.out.println("ObjectMapperConfigConcreteDemo — done.");
  }
}
