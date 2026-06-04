package snippets;

/**
 * Index of all runnable snippet classes (run each {@code main} from IDE).
 */
public final class SnippetCatalog {

  private SnippetCatalog() {}

  // --- DSL & HTTP ---
  public static final String DSL = "dsl.StaticApiAndDslDemo";
  public static final String HTTP_ALL = "http.AllHttpMethodsDemo";

  // --- Request building ---
  public static final String PARAMS = "params.PathQueryFormParamsDemo";
  public static final String REQUEST_PARAMS = "params.RequestParametersDemo";
  public static final String HEADERS_COOKIES = "headers.HeadersAndCookiesDemo";
  public static final String CONTENT_TYPE = "request.ContentTypeAcceptCharsetDemo";
  public static final String FILE_BODY = "request.FileClasspathBodyDemo";
  public static final String FORM_URLENC = "form.FormUrlEncodedConcreteDemo";

  // --- Specs & builders ---
  public static final String REQ_SPEC = "specification.RequestSpecificationConcreteDemo";
  public static final String RES_SPEC = "specification.ResponseSpecificationConcreteDemo";
  public static final String SPEC_BUILDERS = "builders.SpecBuildersConcreteDemo";
  public static final String MULTIPART_BUILDER = "builders.MultiPartSpecBuilderConcreteDemo";

  // --- Auth ---
  public static final String AUTH_ALL = "authentication.AllAuthTypesDemo";
  public static final String OAUTH1 = "authentication.OAuth1ConcreteDemo";
  public static final String OAUTH2_FILTER = "filters.OAuth2TokenRefreshFilterDemo";

  // --- Response ---
  public static final String EXTRACT = "extract.ExtractResponseConcreteDemo";
  public static final String RESPONSE_BODY = "response.ResponseBodyDemo";
  public static final String CHAINED = "chained.ChainedApiCallsDemo";

  // --- Parsing ---
  public static final String JSON_PATH = "parsing.JsonPathConcreteDemo";
  public static final String JSON_PATH_ADV = "parsing.JsonPathAdvancedConcreteDemo";
  public static final String XML_PATH = "parsing.XmlPathConcreteDemo";
  public static final String HTML_PATH = "parsing.HtmlPathConcreteDemo";

  // --- Assertions ---
  public static final String BODY_VALIDATION = "assertions.ResponseBodyValidationDemo";
  public static final String MATCHERS = "assertions.MatchersDemo";
  public static final String ROOT_PATH = "assertions.RootPathAndAdvancedAssertionsDemo";
  public static final String STATUS_COOKIE = "assertions.StatusLineAndCookieAssertionsDemo";
  public static final String JSON_SCHEMA = "assertions.JsonSchemaValidationConcreteDemo";
  public static final String GROOVY_BODY = "assertions.GroovyJsonBodyAssertionsDemo";

  // --- Serialization ---
  public static final String SERIALIZE = "serialization.SerializationConcreteDemo";
  public static final String DESERIALIZE = "serialization.DeserializationConcreteDemo";

  // --- Upload ---
  public static final String MULTIPART = "upload.MultiPartAndBinaryDemo";

  // --- Filters & logging ---
  public static final String FILTERS = "filters.AllFiltersDemo";
  public static final String BUILTIN_FILTERS = "filters.BuiltInFiltersDemo";
  public static final String LOGGING = "logging.LoggingConcreteDemo";

  // --- Config ---
  public static final String GLOBAL_CONFIG = "config.GlobalConfigDemo";
  public static final String ALL_CONFIG = "config.AllConfigurationFeaturesDemo";
  public static final String DECODER = "config.DecoderConfigConcreteDemo";
  public static final String OBJECT_MAPPER = "config.ObjectMapperConfigConcreteDemo";
  public static final String PROXY = "config.ProxyEncodingConcreteDemo";

  // --- Other ---
  public static final String GRAPHQL = "graphql.GraphQLRequestDemo";
  public static final String GLOBAL_SETUP = "config.RestAssuredGlobalSetupDemo";
}
