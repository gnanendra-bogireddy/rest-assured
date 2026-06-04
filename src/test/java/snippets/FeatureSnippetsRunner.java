package snippets;

import assertions.*;
import authentication.AllAuthTypesDemo;
import authentication.OAuth1ConcreteDemo;
import builders.MultiPartSpecBuilderConcreteDemo;
import builders.SpecBuildersConcreteDemo;
import chained.ChainedApiCallsDemo;
import config.*;
import dsl.StaticApiAndDslDemo;
import extract.ExtractResponseConcreteDemo;
import filters.AllFiltersDemo;
import filters.BuiltInFiltersDemo;
import filters.OAuth2TokenRefreshFilterDemo;
import form.FormUrlEncodedConcreteDemo;
import graphql.GraphQLRequestDemo;
import headers.HeadersAndCookiesDemo;
import http.AllHttpMethodsDemo;
import logging.LoggingConcreteDemo;
import oauth.OAuth2ClientCredentialsDemo;
import params.PathQueryFormParamsDemo;
import params.RequestParametersDemo;
import parsing.*;
import request.ContentTypeAcceptCharsetDemo;
import request.FileClasspathBodyDemo;
import serialization.DeserializationConcreteDemo;
import serialization.SerializationConcreteDemo;
import specification.RequestSpecificationConcreteDemo;
import specification.ResponseSpecificationConcreteDemo;
import upload.MultiPartAndBinaryDemo;
import validation.ValidatableResponseDemo;
import io.restassured.RestAssured;

/**
 * Runs every concrete REST Assured snippet. See {@link SnippetCatalog} for class names.
 */
public class FeatureSnippetsRunner {

  public static void main(String[] args) throws Exception {
    RestAssured.reset();
    String[] none = new String[0];

    // DSL & HTTP
    run("StaticApiAndDsl", () -> StaticApiAndDslDemo.main(none));
    run("AllHttpMethods", () -> AllHttpMethodsDemo.main(none));

    // Request
    run("PathQueryFormParams", () -> PathQueryFormParamsDemo.main(none));
    run("RequestParameters", () -> RequestParametersDemo.main(none));
    run("HeadersAndCookies", () -> HeadersAndCookiesDemo.main(none));
    run("ContentTypeAccept", () -> ContentTypeAcceptCharsetDemo.main(none));
    run("FileClasspathBody", () -> FileClasspathBodyDemo.main(none));
    run("FormUrlEncoded", () -> FormUrlEncodedConcreteDemo.main(none));

    // Specs & builders
    run("RequestSpecification", () -> RequestSpecificationConcreteDemo.main(none));
    run("ResponseSpecification", () -> ResponseSpecificationConcreteDemo.main(none));
    run("SpecBuilders", () -> SpecBuildersConcreteDemo.main(none));
    run("MultiPartSpecBuilder", () -> MultiPartSpecBuilderConcreteDemo.main(none));

    // Auth
    run("AllAuthTypes", () -> AllAuthTypesDemo.main(none));
    run("OAuth1", () -> OAuth1ConcreteDemo.main(none));
    run("OAuth2ClientCredentials", () -> OAuth2ClientCredentialsDemo.main(none));
    run("OAuth2TokenRefreshFilter", () -> OAuth2TokenRefreshFilterDemo.main(none));

    // Response & chain
    run("ExtractResponse", () -> ExtractResponseConcreteDemo.main(none));
    run("ChainedApiCalls", () -> ChainedApiCallsDemo.main(none));
    run("ValidatableResponse", () -> ValidatableResponseDemo.main(none));

    // Parsing
    run("JsonPath", () -> JsonPathConcreteDemo.main(none));
    run("JsonPathAdvanced", () -> JsonPathAdvancedConcreteDemo.main(none));
    run("XmlPath", () -> XmlPathConcreteDemo.main(none));
    run("HtmlPath", () -> HtmlPathConcreteDemo.main(none));

    // Assertions
    run("ResponseBodyValidation", () -> ResponseBodyValidationDemo.main(none));
    run("Matchers", () -> MatchersDemo.main(none));
    run("RootPathAdvanced", () -> RootPathAndAdvancedAssertionsDemo.main(none));
    run("StatusLineCookie", () -> StatusLineAndCookieAssertionsDemo.main(none));
    run("GroovyJsonBody", () -> GroovyJsonBodyAssertionsDemo.main(none));
    run("JsonSchemaValidation", () -> JsonSchemaValidationConcreteDemo.main(none));

    // Serialization & upload
    run("Serialization", () -> SerializationConcreteDemo.main(none));
    run("Deserialization", () -> DeserializationConcreteDemo.main(none));
    run("MultiPartAndBinary", () -> MultiPartAndBinaryDemo.main(none));

    // Filters & logging
    run("AllFilters", () -> AllFiltersDemo.main(none));
    run("BuiltInFilters", () -> BuiltInFiltersDemo.main(none));
    run("Logging", () -> LoggingConcreteDemo.main(none));

    // Configuration
    run("GlobalConfig", () -> GlobalConfigDemo.main(none));
    run("GlobalSetup", () -> RestAssuredGlobalSetupDemo.main(none));
    run("AllConfiguration", () -> AllConfigurationFeaturesDemo.main(none));
    run("DecoderConfig", () -> DecoderConfigConcreteDemo.main(none));
    run("ObjectMapperConfig", () -> ObjectMapperConfigConcreteDemo.main(none));

    // GraphQL
    run("GraphQL", () -> GraphQLRequestDemo.main(none));

    RestAssured.reset();
    System.out.println("\n=== All feature snippets completed (" + 40 + " modules) ===");
  }

  @FunctionalInterface
  interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static void run(String name, ThrowingRunnable r) throws Exception {
    System.out.println("\n########## " + name + " ##########");
    r.run();
  }
}
